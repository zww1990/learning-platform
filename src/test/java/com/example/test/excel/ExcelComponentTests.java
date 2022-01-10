package com.example.test.excel;

import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class ExcelComponentTests {
	private AreaExcelComponent areaExcelComponent = new AreaExcelComponent();
	private MenuExcelComponent menuExcelComponent = new MenuExcelComponent();

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

}
