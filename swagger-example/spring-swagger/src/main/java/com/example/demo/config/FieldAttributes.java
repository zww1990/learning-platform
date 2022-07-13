package com.example.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * FieldAttributes
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:21:28
 */
@Getter
@AllArgsConstructor
@ToString
public class FieldAttributes {
	private String objectName;
	private String field;
	private String errorMessage;

}
