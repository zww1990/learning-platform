package io.example.demo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

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

}
