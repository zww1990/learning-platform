package com.example.demo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus
	public Map<String, Object> hander(HttpServletRequest request, Exception ex) {
		String message = ex.getLocalizedMessage();
		String url = request.getRequestURL().toString();
		log.error("API请求地址>>>{}", url);
		log.error(message, ex);
		Map<String, Object> error = new HashMap<>();
		error.put("code", false);
		error.put("message", message);
		error.put("url", url);
		return error;
	}
}
