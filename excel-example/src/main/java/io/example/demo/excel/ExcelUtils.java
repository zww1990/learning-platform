package io.example.demo.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.extern.slf4j.Slf4j;

/**
 * ExcelUtils
 * 
 * @author zhang weiwei
 * @since 2022年10月21日,下午7:51:12
 */
@Slf4j
public abstract class ExcelUtils {

	/**
	 * 合并多个excel文件
	 * 
	 * @param fileList        excel文件路径
	 * @param folder          目标文件保存目录
	 * @param dateTimePattern 日期时间格式
	 */
	public static void mergeExcel(List<String> fileList, String folder, String dateTimePattern) {
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
					for (int i = 0; i < number; i++) {
						Sheet srcSheet = srcWorkbook.getSheetAt(i);
						log.info("正在读取工作表[ {} ]", srcSheet.getSheetName());
						Sheet tarSheet = tarWorkbook.getSheet(srcSheet.getSheetName());
						boolean isNew = tarSheet == null;
						if (isNew) {
							log.info("正在创建工作表[ {} ]", srcSheet.getSheetName());
							tarSheet = tarWorkbook.createSheet(srcSheet.getSheetName());
						}
						try {
							// 复制sheet内容
							copyExcelSheet(tarWorkbook, srcSheet, tarSheet, isNew);
							log.info("工作表[ {} ]已合并", tarSheet.getSheetName());
						} catch (Exception e) {
							log.error(e.getLocalizedMessage(), e);
						}
					}
				}
			}
			Path path = Paths.get(folder);
			if (Files.notExists(path)) {
				log.info("正在创建文件夹[ {} ]", folder);
				Files.createDirectory(path);
			}
			// 新生成的excel文件
			String dateTime = DateTimeFormatter.ofPattern(dateTimePattern).format(LocalDateTime.now());
			String fullPath = String.format("%s%s%s.xlsx", folder, File.separator, dateTime);
			File file = new File(fullPath);
			// 判断文件是否存在
			if (file.exists()) {
				log.info("正在删除工作簿[ {} ]", fullPath);
				// 存在则删除
				file.delete();
			}
			// 使用输出流写出
			try (OutputStream out = new FileOutputStream(file)) {
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
	 * @param workbook excel工作簿
	 * @param srcSheet 来源sheet
	 * @param tarSheet 目标sheet
	 * @param isNew    是否新建的sheet
	 */
	private static void copyExcelSheet(Workbook workbook, Sheet srcSheet, Sheet tarSheet, boolean isNew) {
		// 合并单元格
		mergeSheetAllRegion(srcSheet, tarSheet);
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
				if (srcRow.getRowNum() == 0) {
					// 从第二个工作簿开始，遇到表头行，直接跳过
					continue;
				}
				rowNum = tarSheet.getLastRowNum() + 1;
			}
			// 创建新行
			Row tarRow = tarSheet.createRow(rowNum);
			// 复制行
			copyExcelRow(workbook, srcRow, tarRow);
		}
	}

	/**
	 * 合并单元格
	 * 
	 * @param srcSheet 来源sheet
	 * @param tarSheet 目标sheet
	 */
	private static void mergeSheetAllRegion(Sheet srcSheet, Sheet tarSheet) {
		for (int i = 0, num = srcSheet.getNumMergedRegions(); i < num; i++) {
			tarSheet.addMergedRegion(srcSheet.getMergedRegion(i));
		}
	}

	/**
	 * 复制excel中的行到新的sheet中
	 * 
	 * @param workbook 目标工作簿
	 * @param srcRow   来源excel行
	 * @param tarRow   目标excel行
	 */
	private static void copyExcelRow(Workbook workbook, Row srcRow, Row tarRow) {
		// 设置行高
		tarRow.setHeight(srcRow.getHeight());
		// 获取所有列
		for (Cell srcCell : srcRow) {
			// 创建单元格
			Cell tarCell = tarRow.createCell(srcCell.getColumnIndex());
			// 复制单元格
			copyExcelCell(workbook, srcCell, tarCell);
		}
	}

	/**
	 * 复制单元格
	 * 
	 * @param workbook 目标工作簿
	 * @param srcCell  来源excel单元格
	 * @param tarCell  目标excel单元格
	 */
	private static void copyExcelCell(Workbook workbook, Cell srcCell, Cell tarCell) {
		CellStyle cellStyle = workbook.createCellStyle();
		// 复制单元格样式
		cellStyle.cloneStyleFrom(srcCell.getCellStyle());
		// 单元格样式
		tarCell.setCellStyle(cellStyle);
		if (srcCell.getCellComment() != null) {
			tarCell.setCellComment(srcCell.getCellComment());
		}
		// 不同数据类型处理
		CellType cellType = srcCell.getCellTypeEnum();
		tarCell.setCellType(cellType);
		if (cellType == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(srcCell)) {
				tarCell.setCellValue(srcCell.getDateCellValue());
			} else {
				tarCell.setCellValue(srcCell.getNumericCellValue());
			}
		} else if (cellType == CellType.STRING) {
			tarCell.setCellValue(srcCell.getRichStringCellValue());
		} else if (cellType == CellType.BOOLEAN) {
			tarCell.setCellValue(srcCell.getBooleanCellValue());
		} else if (cellType == CellType.ERROR) {
			tarCell.setCellErrorValue(srcCell.getErrorCellValue());
		} else if (cellType == CellType.FORMULA) {
			tarCell.setCellFormula(srcCell.getCellFormula());
		} else {
		}
	}

}
