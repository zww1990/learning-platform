package io.example.demo.excel;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ExcelData
 * 
 * @author zww1990@foxmail.com
 * @since 2022年1月8日,下午4:51:46
 */
@Getter
@Setter
@ToString
public class ExcelData {
	private String cellValue;
	private List<ExcelData> childrens;
}
