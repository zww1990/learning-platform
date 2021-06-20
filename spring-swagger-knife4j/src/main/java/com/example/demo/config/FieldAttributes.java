package com.example.demo.config;

public class FieldAttributes {
	private String objectName;
	private String field;
	private String errorMessage;

	public FieldAttributes(String objectName, String field, String errorMessage) {
		super();
		this.objectName = objectName;
		this.field = field;
		this.errorMessage = errorMessage;
	}

	public String getObjectName() {
		return objectName;
	}

	public String getField() {
		return field;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
