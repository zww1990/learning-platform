package io.example.demo.excel;

import io.example.demo.config.ApplicationProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * ExcelUtils
 *
 * @author zhang weiwei
 * @since 2022年10月21日, 下午7:51:12
 */
@Slf4j
public abstract class ExcelUtils {

    /**
     * 合并多个excel文件
     *
     * @param fileList   excel文件路径
     * @param properties 应用程序配置
     */
    public static void mergeExcel(List<String> fileList, ApplicationProperties properties) {
        // 创建新的excel工作簿
        try (Workbook tarWorkbook = new XSSFWorkbook()) {
            log.info("开始合并工作簿");
            // 遍历需要合并的excel文件
            for (int j = 0, size = fileList.size(); j < size; j++) {
                String filePath = fileList.get(j);
                log.info("正在读取第[ {} ]个工作簿[ {} ]", j + 1, filePath);
                // 读取工作簿
                try (Workbook srcWorkbook = WorkbookFactory.create(new File(filePath))) {
                    // 获取工作簿中的Sheet个数
                    int number = srcWorkbook.getNumberOfSheets();
                    log.info("工作表总个数[ {} ]", number);
                    // 创建一个公式计算器
                    FormulaEvaluator evaluator = srcWorkbook.getCreationHelper().createFormulaEvaluator();
                    for (int i = 0; i < number; i++) {
                        Sheet srcSheet = srcWorkbook.getSheetAt(i);
                        log.info("正在读取工作表[ {} ]", srcSheet.getSheetName());
                        // 如果是一个空sheet，不进行复制
                        if (srcSheet.getFirstRowNum() == 0
                                && srcSheet.getLastRowNum() == 0
                                && srcSheet.getPhysicalNumberOfRows() == 0) {
                            log.info("工作表[ {} ]没有数据，不合并", srcSheet.getSheetName());
                            continue;
                        }
                        Sheet tarSheet = tarWorkbook.getSheet(srcSheet.getSheetName());
                        boolean isNew = tarSheet == null;
                        if (isNew) {
                            log.info("正在创建工作表[ {} ]", srcSheet.getSheetName());
                            tarSheet = tarWorkbook.createSheet(srcSheet.getSheetName());
                        }
                        try {
                            // 复制sheet内容
                            copyExcelSheet(srcSheet, tarSheet, isNew, evaluator);
                            log.info("工作表[ {} ]已合并", tarSheet.getSheetName());
                        } catch (Exception e) {
                            log.error(e.getLocalizedMessage(), e);
                        }
                    }
                }
            }
            Path path = Paths.get(properties.getWriteFolder());
            if (Files.notExists(path)) {
                log.info("正在创建文件夹[ {} ]", properties.getWriteFolder());
                Files.createDirectory(path);
            }
            // 新生成的excel文件
            String dateTime = DateTimeFormatter.ofPattern(properties.getDateTimePattern()).format(LocalDateTime.now());
            String fullPath = String.format("%s%s%s.xlsx", properties.getWriteFolder(), File.separator, dateTime);
            File file = new File(fullPath);
            // 判断文件是否存在
            if (file.exists()) {
                log.info("正在删除工作簿[ {} ]", fullPath);
                // 存在则删除
                file.delete();
            }
            // 使用输出流写出
            try (OutputStream out = Files.newOutputStream(file.toPath())) {
                log.info("正在写入工作簿[ {} ]", fullPath);
                tarWorkbook.write(out);
                out.flush();
            }
            log.info("工作簿合并完成");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 复制sheet到新的excel文件中
     *
     * @param srcSheet  来源sheet
     * @param tarSheet  目标sheet
     * @param isNew     是否新建的sheet
     * @param evaluator 公式计算器
     */
    private static void copyExcelSheet(Sheet srcSheet, Sheet tarSheet, boolean isNew, FormulaEvaluator evaluator) {
        // 获取最后一个单元格位置
        int cellNum = srcSheet.getRow(srcSheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i < cellNum; i++) {
            // 设置单元格列宽度
            tarSheet.setColumnWidth(i, srcSheet.getColumnWidth(i));
        }
        // 复制每行内容
        for (Row srcRow : srcSheet) {
            int rowNum;
            if (isNew) {
                rowNum = srcRow.getRowNum();
            } else {
                rowNum = tarSheet.getLastRowNum() + 1;
            }
            // 创建新行
            Row tarRow = tarSheet.createRow(rowNum);
            // 复制行
            copyExcelRow(srcRow, tarRow, tarSheet, evaluator);
        }
    }

    /**
     * 复制excel中的行到新的sheet中
     *
     * @param srcRow    来源excel行
     * @param tarRow    目标excel行
     * @param tarSheet  目标sheet
     * @param evaluator 公式计算器
     */
    private static void copyExcelRow(Row srcRow, Row tarRow, Sheet tarSheet, FormulaEvaluator evaluator) {
        // 设置行高
        tarRow.setHeight(srcRow.getHeight());
        // 是否有值
        boolean hasValue = false;
        // 获取所有列
        for (Cell srcCell : srcRow) {
            // 创建单元格
            Cell tarCell = tarRow.createCell(srcCell.getColumnIndex());
            if (srcCell.getCellComment() != null) {
                tarCell.setCellComment(srcCell.getCellComment());
            }
            // 不同数据类型处理
            CellType cellType = srcCell.getCellType();
            if (cellType == CellType.NUMERIC) {
                if (DateUtil.isCellDateFormatted(srcCell)) {
                    tarCell.setCellValue(srcCell.getDateCellValue());
                    if (tarCell.getDateCellValue() != null) {
                        hasValue = true;
                    }
                } else {
                    tarCell.setCellValue(srcCell.getNumericCellValue());
                    if (tarCell.getNumericCellValue() != 0) {
                        hasValue = true;
                    }
                }
            } else if (cellType == CellType.STRING) {
                tarCell.setCellValue(srcCell.getRichStringCellValue());
                if (tarCell.getRichStringCellValue().length() != 0) {
                    hasValue = true;
                }
            } else if (cellType == CellType.BOOLEAN) {
                tarCell.setCellValue(srcCell.getBooleanCellValue());
                if (tarCell.getBooleanCellValue()) {
                    hasValue = true;
                }
            } else if (cellType == CellType.ERROR) {
                tarCell.setCellErrorValue(srcCell.getErrorCellValue());
                if (tarCell.getErrorCellValue() != 0) {
                    hasValue = true;
                }
            } else if (cellType == CellType.FORMULA) {
                CellValue evaluate = evaluator.evaluate(srcCell);
                if (evaluate.getCellType() == CellType.BOOLEAN) {
                    tarCell.setCellValue(evaluate.getBooleanValue());
                    hasValue = true;
                } else if (evaluate.getCellType() == CellType.NUMERIC) {
                    tarCell.setCellValue(evaluate.getNumberValue());
                    hasValue = true;
                } else if (evaluate.getCellType() == CellType.STRING) {
                    tarCell.setCellValue(evaluate.getStringValue());
                    hasValue = true;
                }
            }
        }
        if (!hasValue) {
            // 当前行没有一个值，删除当前空白行
            tarSheet.removeRow(tarRow);
        }
    }

}
