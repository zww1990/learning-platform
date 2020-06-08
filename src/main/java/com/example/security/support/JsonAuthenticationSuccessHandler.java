package com.example.security.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.security.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;

/**
 * 用于处理成功的用户身份验证的策略。
 * 
 * @author home
 */
@SuppressWarnings("deprecation")
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	@Resource
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);// 登录成功后删除验证码
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (PrintWriter out = response.getWriter()) {
			User user = (User) authentication.getPrincipal();
			user.setPassword(null);
			out.write(this.objectMapper.writeValueAsString(user));
		}
	}

}
