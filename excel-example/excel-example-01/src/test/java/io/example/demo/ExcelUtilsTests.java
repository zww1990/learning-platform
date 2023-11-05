package io.example.demo;

import io.example.demo.config.ApplicationProperties;
import io.example.demo.excel.ExcelUtils;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ExcelUtilsTests
 *
 * @author zhang weiwei
 * @since 2022年8月12日, 下午8:44:10
 */
public class ExcelUtilsTests {

    @Test
    public void testMergeExcel() {
        try {
            List<String> fileList = Arrays.asList("E:\\校区案例11.xlsx", "E:\\校区案例22.xlsx");
            String filePath = "E:\\";
            String dateTime = "yyyy-MM-dd_HH-mm-ss";
            ApplicationProperties ap = new ApplicationProperties();
            ap.setDateTimePattern(dateTime);
            ap.setWriteFolder(filePath);
            ExcelUtils.mergeExcel(fileList, ap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReadExcel() {
        File file = new File("D:\\合并\\待合并的工作簿\\【拓展校区】FY25预算-长沙分校.xlsx");
        try (Workbook wb = WorkbookFactory.create(file)) {
            FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
            List<List<Object>> data = new ArrayList<>();
            for (Sheet sheet : wb) {
                System.err.println("正在读取: " + sheet.getSheetName() +
                        ", getFirstRowNum: " + sheet.getFirstRowNum() +
                        ", getLastRowNum: " + sheet.getLastRowNum() +
                        ", getPhysicalNumberOfRows: " + sheet.getPhysicalNumberOfRows());
                for (Row row : sheet) {
                    List<Object> item = new ArrayList<>();
                    item.add(row.getRowNum());
                    boolean hasValue = false;
                    for (Cell cell : row) {
                        CellType cellType = cell.getCellType();
                        Object value = null;
                        if (cellType == CellType.BOOLEAN) {
                            boolean tmp = cell.getBooleanCellValue();
                            if (tmp) {
                                hasValue = true;
                            }
                            value = tmp;
                        } else if (cellType == CellType.ERROR) {
                            byte tmp = cell.getErrorCellValue();
                            if (tmp != 0) {
                                hasValue = true;
                            }
                            value = tmp;
                        } else if (cellType == CellType.FORMULA) {
                            CellValue cellValue = evaluator.evaluate(cell);
                            if (cellValue.getCellType() == CellType.BOOLEAN) {
                                value = cellValue.getBooleanValue();
                                hasValue = true;
                            } else if (cellValue.getCellType() == CellType.NUMERIC) {
                                value = cellValue.getNumberValue();
                                hasValue = true;
                            } else if (cellValue.getCellType() == CellType.STRING) {
                                value = cellValue.getStringValue();
                                hasValue = true;
                            } else {
                                hasValue = false;
                            }
                        } else if (cellType == CellType.NUMERIC) {
                            double tmp = cell.getNumericCellValue();
                            if (tmp != 0) {
                                hasValue = true;
                            }
                            value = tmp;
                        } else if (cellType == CellType.STRING) {
                            String tmp = cell.getStringCellValue();
                            if (StringUtils.hasText(tmp)) {
                                hasValue = true;
                            }
                            value = tmp;
                        }
                        item.add(value);
                    }
//                    System.err.println(item);
//					System.err.println(String.format(
//							"RowNum=%s, FirstCellNum=%s, LastCellNum=%s, PhysicalNumberOfCells=%s, hasValue=%s",
//							row.getRowNum(), row.getFirstCellNum(), row.getLastCellNum(),
//							row.getPhysicalNumberOfCells(), hasValue));
                    if (hasValue) {
                        data.add(item);
                    }
                }
            }
//            data.forEach(System.err::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
