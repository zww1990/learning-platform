package io.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import io.example.demo.excel.ExcelUtils;

/**
 * ExcelUtilsTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:44:10
 */
public class ExcelUtilsTests {

	@Test
	public void testMergeExcel() {
		try {
			List<String> fileList = Arrays.asList("E:\\校区案例11.xlsx", "E:\\校区案例22.xlsx");
			String filePath = "E:\\";
			String dateTime = "yyyy-MM-dd_HH-mm-ss";
			ExcelUtils.mergeExcel(fileList, filePath, dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadExcel() {
		File file = new File("D:\\待合并的工作簿\\【拓展校区】TY24预算-北京分校.xlsx");
		try (Workbook wb = WorkbookFactory.create(file)) {
			List<List<Object>> data = new ArrayList<>();
			for (Sheet sheet : wb) {
				for (Row row : sheet) {
					List<Object> item = new ArrayList<>();
					item.add(row.getRowNum());
					boolean hasValue = false;
					for (Cell cell : row) {
						CellType cellType = cell.getCellTypeEnum();
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
							String tmp = cell.getCellFormula();
							if (StringUtils.hasText(tmp)) {
								hasValue = true;
							}
							value = tmp;
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
//					System.err.println(String.format(
//							"RowNum=%s, FirstCellNum=%s, LastCellNum=%s, PhysicalNumberOfCells=%s, hasValue=%s",
//							row.getRowNum(), row.getFirstCellNum(), row.getLastCellNum(),
//							row.getPhysicalNumberOfCells(), hasValue));
					if (hasValue) {
						data.add(item);
					}
				}
			}
			data.forEach(System.err::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
