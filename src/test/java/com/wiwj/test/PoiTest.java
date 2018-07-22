package com.wiwj.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class PoiTest {
	@Test
	public void testCreate() {
		File file = new File("E:\\projects\\test.xlsx");
		try (Workbook wb = new XSSFWorkbook(); OutputStream stream = new FileOutputStream(file)) {
			Name name = this.createSheet1(wb);
			Sheet sheet = wb.createSheet("数据验证");
			DataValidationHelper helper = new XSSFDataValidationHelper((XSSFSheet) sheet);
			DataValidationConstraint constraint = helper.createFormulaListConstraint(name.getNameName());
			CellRangeAddressList addressList = new CellRangeAddressList(1, // 起始行号
					65535, // 终止行号
					1, // 起始列号
					1// 终止列号
			);
			DataValidation validation = helper.createValidation(constraint, addressList);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			sheet.addValidationData(validation);
			wb.setActiveSheet(wb.getSheetIndex(sheet));// 设置活动的sheet页
			wb.write(stream);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Name createSheet1(Workbook wb) {
		List<String> list = Arrays.asList("一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十",
				"一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十", "一二三四五六七八九十");
		Sheet sheet = wb.createSheet("数据源");
		for (int i = 0, size = list.size(); i < size; i++) {
			sheet.createRow(i).createCell(0).setCellValue(list.get(i));
		}
		Name name = wb.createName();
		name.setRefersToFormula("数据源!$A$1:$A$65535");
		name.setNameName("数据源");
		wb.setSheetHidden(wb.getSheetIndex(sheet), true);// 设置隐藏的sheet页
		return name;
	}
}
