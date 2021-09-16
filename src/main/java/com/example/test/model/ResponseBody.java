package com.example.test.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
@SuppressWarnings("unchecked")
public class ResponseBody {
	private int status;
	private int code;
	private String message;
	private Object data;

	public <T> T getData() {
		return (T) data;
	}
}
