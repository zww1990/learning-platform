package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * FieldAttributes
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:58:41
 */
@Getter
@ToString
@AllArgsConstructor
public class FieldAttributes {
	private String objectName;
	private String field;
	private String errorMessage;

}
