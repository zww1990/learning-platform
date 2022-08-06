package com.example.hello.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ResponseBody<T> {
	private int status;
	private int code;
	private String message;
	private T data;

}
