package com.example.test.excel;

import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * ExcelComponent
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午11:06:00
 */
public interface ExcelComponent {
	/**
	 * 写入Excel
	 * 
	 * @param os 输出流
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:06:09
	 */
	void write(OutputStream os);

	/**
	 * 添加简单验证数据
	 * 
	 * @param sheet sheet页
	 * @param data  验证数据
	 * @param index 第几列，从0开始
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:06:45
	 */
	default void addSimpleValidationData(XSSFSheet sheet, String[] data, int index) {
		DataValidationHelper helper = new XSSFDataValidationHelper(sheet);
		DataValidationConstraint constraint = helper.createExplicitListConstraint(data);
		CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, index, index);
		DataValidation validation = helper.createValidation(constraint, addressList);
		validation.setSuppressDropDownArrow(true);// 提供下拉箭头
		validation.setShowErrorBox(true);// 输入无效数据时显示出错警告
		validation.setShowPromptBox(true);// 选定单元格时显示输入信息
		sheet.addValidationData(validation);
	}

	/**
	 * 添加公式列表验证数据
	 * 
	 * @param sheet       sheet页
	 * @param listFormula 列表公式
	 * @param index       第几列，从0开始
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:07:08
	 */
	default void addFormulaListValidationData(XSSFSheet sheet, String listFormula, int index) {
		DataValidationHelper helper = new XSSFDataValidationHelper(sheet);
		DataValidationConstraint constraint = helper.createFormulaListConstraint(listFormula);
		CellRangeAddressList addressList = new CellRangeAddressList(1, 65535, index, index);
		DataValidation validation = helper.createValidation(constraint, addressList);
		validation.setSuppressDropDownArrow(true);// 提供下拉箭头
		validation.setShowErrorBox(true);// 输入无效数据时显示出错警告
		validation.setShowPromptBox(true);// 选定单元格时显示输入信息
		sheet.addValidationData(validation);
	}

	/**
	 * 创建表头行
	 * 
	 * @param wb      工作簿
	 * @param sheet   sheet页
	 * @param headers 表头行数据
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:07:26
	 */
	default void createHeaderRow(Workbook wb, XSSFSheet sheet, String[] headers) {
		Row row = sheet.createRow(0);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);// 设置水平对齐
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置垂直对齐
		style.setFillForegroundColor(IndexedColors.ROYAL_BLUE.index);// 设置填充前景色
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 设置填充图案
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);// 设置字体大小
		font.setFontName("Microsoft YaHei UI");// 设置字体名称
		font.setColor(IndexedColors.WHITE.index);// 设置字体颜色
		font.setBold(true);// 设置是否加粗
		style.setFont(font);
		for (int i = 0, length = headers.length; i < length; i++) {
			String header = headers[i];
			Cell cell = row.createCell(i);
			cell.setCellValue(header);
			cell.setCellStyle(style);// 设置单元格样式
			sheet.setColumnWidth(i, header.getBytes().length * 256 * 2);// 设置单元格宽度
		}
	}

	/**
	 * 计算范围
	 * 
	 * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
	 * @param rowId    第几行
	 * @param colCount 一共多少列
	 * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
	 * @author zww1990@foxmail.com
	 * @since 2022年1月8日,下午11:07:39
	 */
	default String calcRange(int offset, int rowId, int colCount) {
		char start = (char) ('A' + offset);
		if (colCount <= 25) {
			char end = (char) (start + colCount - 1);
			return new StringBuilder()//
					.append('$')//
					.append(start)//
					.append('$')//
					.append(rowId)//
					.append(":$")//
					.append(end)//
					.append('$')//
					.append(rowId)//
					.toString();
		}
		char endPrefix = 'A';
		char endSuffix;
		if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
			if ((colCount - 25) % 26 == 0) {// 边界值
				endSuffix = (char) ('A' + 25);
			} else {
				endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
			}
		} else {// 51以上
			if ((colCount - 25) % 26 == 0) {
				endSuffix = (char) ('A' + 25);
				endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
			} else {
				endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
				endPrefix = (char) (endPrefix + (colCount - 25) / 26);
			}
		}
		return new StringBuilder()//
				.append('$')//
				.append(start)//
				.append('$')//
				.append(rowId)//
				.append(":$")//
				.append(endPrefix)//
				.append(endSuffix)//
				.append('$')//
				.append(rowId)//
				.toString();
	}
}
