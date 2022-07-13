package com.example.security.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 会话信息过期策略，它将错误消息写入响应正文。
 * 
 * @author home
 */
@SuppressWarnings("deprecation")
public class JsonSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Resource
	private ObjectMapper objectMapper;

	public JsonSessionInformationExpiredStrategy() {
		super();
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		try (PrintWriter out = response.getWriter()) {
			out.write(this.objectMapper.writeValueAsString(Arrays.asList("该会话已过期（可能是由于尝试以同一用户的身份进行多个并发登录）。")));
		}
	}

}
