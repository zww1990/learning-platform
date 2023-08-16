package com.example.security.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用于处理失败的身份验证尝试的策略。
 * 
 * @author home
 */
@SuppressWarnings("deprecation")
public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Resource
	private ObjectMapper objectMapper;

	public JsonAuthenticationFailureHandler() {
		super();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		try (PrintWriter out = response.getWriter()) {
			out.write(this.objectMapper.writeValueAsString(Arrays.asList(exception.toString())));
		}
	}

}
