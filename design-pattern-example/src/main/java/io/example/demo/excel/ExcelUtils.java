package io.example.demo.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * ExcelUtils
 * 
 * @author zhang weiwei
 * @since 2022年10月21日,下午7:51:12
 */
public abstract class ExcelUtils {

	/**
	 * 合并多个excel文件
	 * 
	 * @param fileList excel文件路径
	 * @param filePath 目标文件保存目录
	 * @param fileName 目标文件名称
	 */
	public static void mergeExcel(List<String> fileList, String filePath, String fileName) {
		// 创建新的excel工作簿
		Workbook writeWorkbook = new XSSFWorkbook();
		// 遍历需要合并的excel文件
		for (String excelName : fileList) {
			try (InputStream in = new FileInputStream(excelName);
					// 读取工作簿
					Workbook readWorkbook = new XSSFWorkbook(in)) {
				// 获取工作簿中的Sheet个数
				int len = readWorkbook.getNumberOfSheets();
				for (int i = 0; i < len; i++) {
					Sheet source = readWorkbook.getSheetAt(i);
					Sheet target = writeWorkbook.getSheet(source.getSheetName());
					boolean isNew = target == null;
					if (isNew) {
						target = writeWorkbook.createSheet(source.getSheetName());
					}
					// 复制sheet内容
					copyExcelSheet(writeWorkbook, source, target, isNew);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 新生成的excel文件
		if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
			fileName += ".xlsx";
		}
		String excelFileName = filePath + File.separator + fileName;
		// 判断文件是否存在
		File excelFile = new File(excelFileName);
		if (excelFile.exists()) {
			// 存在则删除
			excelFile.delete();
		}
		// 使用输出流写出
		try (OutputStream out = new FileOutputStream(excelFileName)) {
			writeWorkbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writeWorkbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.err.println(String.format("excel文件合并成功，合并后文件路径 >>> [ %s ]", excelFileName));
	}

	/**
	 * 复制sheet到新的excel文件中
	 * 
	 * @param workbook excel工作簿
	 * @param source   来源sheet
	 * @param target   目标sheet
	 * @param isNew    是否新建的sheet
	 */
	private static void copyExcelSheet(Workbook workbook, Sheet source, Sheet target, boolean isNew) {
		// 合并单元格
		mergeSheetAllRegion(source, target);
		// 获取最后一个单元格位置
		int len = source.getRow(source.getFirstRowNum()).getLastCellNum();
		for (int i = 0; i < len; i++) {
			// 设置单元格列宽度
			target.setColumnWidth(i, source.getColumnWidth(i));
		}
		// 复制每行内容
		Iterator<Row> it = source.iterator();
		while (it.hasNext()) {
			Row src = it.next();
			int rowNum;
			if (isNew) {
				rowNum = src.getRowNum();
			} else {
				if (src.getRowNum() == 0) {
					// 从第二个工作簿开始，遇到表头行，直接跳过
					continue;
				}
				rowNum = target.getLastRowNum() + 1;
			}
			// 创建新行
			Row tar = target.createRow(rowNum);
			// 复制行
			copyExcelRow(workbook, src, tar);
		}
	}

	/**
	 * 合并单元格
	 * 
	 * @param source 来源sheet
	 * @param target 目标sheet
	 */
	private static void mergeSheetAllRegion(Sheet source, Sheet target) {
		for (int i = 0, num = source.getNumMergedRegions(); i < num; i++) {
			target.addMergedRegion(source.getMergedRegion(i));
		}
	}

	/**
	 * 复制excel中的行到新的sheet中
	 * 
	 * @param workbook 目标工作簿
	 * @param source   来源excel行
	 * @param target   目标excel行
	 */
	private static void copyExcelRow(Workbook workbook, Row source, Row target) {
		// 设置行高
		target.setHeight(source.getHeight());
		// 获取所有列
		Iterator<Cell> it = source.cellIterator();
		while (it.hasNext()) {
			Cell src = it.next();
			// 创建单元格
			Cell tar = target.createCell(src.getColumnIndex());
			// 复制单元格
			copyExcelCell(workbook, src, tar);
		}
	}

	/**
	 * 复制单元格
	 * 
	 * @param workbook 目标工作簿
	 * @param source   来源excel单元格
	 * @param target   目标excel单元格
	 */
	private static void copyExcelCell(Workbook workbook, Cell source, Cell target) {
		CellStyle cellStyle = workbook.createCellStyle();
		// 复制单元格样式
		cellStyle.cloneStyleFrom(source.getCellStyle());
		// 单元格样式
		target.setCellStyle(cellStyle);
		if (source.getCellComment() != null) {
			target.setCellComment(source.getCellComment());
		}
		// 不同数据类型处理
		CellType cellType = source.getCellTypeEnum();
		target.setCellType(cellType);
		if (cellType == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(source)) {
				target.setCellValue(source.getDateCellValue());
			} else {
				target.setCellValue(source.getNumericCellValue());
			}
		} else if (cellType == CellType.STRING) {
			target.setCellValue(source.getRichStringCellValue());
		} else if (cellType == CellType.BOOLEAN) {
			target.setCellValue(source.getBooleanCellValue());
		} else if (cellType == CellType.ERROR) {
			target.setCellErrorValue(source.getErrorCellValue());
		} else if (cellType == CellType.FORMULA) {
			target.setCellFormula(source.getCellFormula());
		} else {
		}
	}

}
