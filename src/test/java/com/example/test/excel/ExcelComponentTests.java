package com.example.test.excel;

import java.io.FileOutputStream;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class ExcelComponentTests {
	private AreaExcelComponent areaExcelComponent = new AreaExcelComponent();

	@Test
	public void testWrite() {
		try {
			this.areaExcelComponent.write(new FileOutputStream(Paths.get("e:", "test.xlsx").toFile()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
