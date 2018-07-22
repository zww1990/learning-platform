package com.wiwj.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
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
			Sheet sheet = wb.createSheet("数据验证");
			DataValidationHelper helper = new XSSFDataValidationHelper((XSSFSheet) sheet);
			DataValidationConstraint constraint = helper
					.createExplicitListConstraint(new String[] { "11", "21", "31" });
			CellRangeAddressList addressList = new CellRangeAddressList(1, // 起始行号
					65535, // 终止行号
					0, // 起始列号
					0// 终止列号
			);
			DataValidation validation = helper.createValidation(constraint, addressList);
			validation.setSuppressDropDownArrow(true);
			validation.setShowErrorBox(true);
			sheet.addValidationData(validation);
			wb.write(stream);
			System.err.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
