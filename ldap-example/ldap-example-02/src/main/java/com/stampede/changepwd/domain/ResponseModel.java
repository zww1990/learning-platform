package com.stampede.changepwd.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ResponseModel {
	private Object status;
	private Object data;
	private String message;
}
