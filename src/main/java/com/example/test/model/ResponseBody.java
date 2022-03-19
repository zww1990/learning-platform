package com.example.test.model;

public class ResponseBody<T> {
	private int status;
	private int code;
	private String message;
	private T data;

	public int getStatus() {
		return status;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public ResponseBody<T> setStatus(int status) {
		this.status = status;
		return this;
	}

	public ResponseBody<T> setCode(int code) {
		this.code = code;
		return this;
	}

	public ResponseBody<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public ResponseBody<T> setData(T data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return String.format("ResponseBody [status=%s, code=%s, message=%s, data=%s]", status, code, message, data);
	}

}
