package com.wiwj.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelTest {
	public static void main(String[] args) {
		File file = new File("D:\\20190815北京门店电话更新.xlsx");
		try (Workbook workbook = WorkbookFactory.create(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				short lastCellNum = row.getLastCellNum();
				List<String> values = new ArrayList<>();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					cell.setCellType(CellType.STRING);
					values.add(cell.getStringCellValue());
				}
				System.err.println(String.format(
						"UPDATE bdm_shops t SET t.service_phone = '%s', t.update_time = systimestamp WHERE t.shop_name = '%s';",
						values.get(6), values.get(1)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
