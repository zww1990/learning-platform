package io.example.demo;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.example.demo.excel.AreaExcelComponent;
import io.example.demo.excel.DemoExcelComponent;
import io.example.demo.excel.ExcelUtils;
import io.example.demo.excel.MenuExcelComponent;

/**
 * ExcelComponentTests
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:44:10
 */
public class ExcelComponentTests {
	private AreaExcelComponent areaExcelComponent = new AreaExcelComponent();
	private MenuExcelComponent menuExcelComponent = new MenuExcelComponent();
	private DemoExcelComponent demoExcelComponent = new DemoExcelComponent();

	@Test
	public void testMergeExcel() {
		try {
			List<String> fileList = Arrays.asList("E:\\校区案例11.xlsx", "E:\\校区案例22.xlsx");
			String filePath = "E:\\";
			String fileName = "合并后的excel";
			ExcelUtils.mergeExcel(fileList, filePath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAreaWrite() {
		try {
			this.areaExcelComponent.write(new FileOutputStream(Paths.get("e:", String
					.join(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()), "area-", ".xlsx"))
					.toFile()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMenuWrite() {
		try {
			this.menuExcelComponent.write(new FileOutputStream(Paths.get("e:", String
					.join(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()), "menu-", ".xlsx"))
					.toFile()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDemoWrite() {
		try {
			this.demoExcelComponent.write(new FileOutputStream(Paths.get("e:", String
					.join(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()), "demo-", ".xlsx"))
					.toFile()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}