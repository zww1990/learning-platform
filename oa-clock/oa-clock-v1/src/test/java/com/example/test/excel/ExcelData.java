package com.example.test.excel;

import java.util.List;

/**
 * ExcelData
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午4:51:46
 */
public class ExcelData {
	private String cellValue;
	private List<ExcelData> childrens;

	public String getCellValue() {
		return cellValue;
	}

	public List<ExcelData> getChildrens() {
		return childrens;
	}

	public ExcelData setCellValue(String cellValue) {
		this.cellValue = cellValue;
		return this;
	}

	public ExcelData setChildrens(List<ExcelData> childrens) {
		this.childrens = childrens;
		return this;
	}

	@Override
	public String toString() {
		return String.format("ExcelData [cellValue=%s, childrens=%s]", cellValue, childrens);
	}
}
