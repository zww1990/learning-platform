package com.example.hello.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * ResponseBody
 * 
 * @author zhang weiwei
 * @since 2022年8月13日,下午8:58:30
 * @param <T>
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ResponseBody<T> {
	/** 成功 */
	public static final int SUCCESS = 1;
	/** 失败 */
	public static final int FAILURE = 0;
	private int status;
	private int code;
	private String message;
	private T data;
}
