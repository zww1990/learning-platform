package com.example.security.support;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 会话信息过期策略，它将错误消息写入响应正文。
 * 
 * @author home
 */
public class ResponseBodySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(MediaType.TEXT_HTML_VALUE);
		try (PrintWriter out = response.getWriter()) {
			out.write("<html>");
			out.write("<head>");
			out.write("<title>会话提示</title>");
			out.write("</head>");
			out.write("<body>");
			out.write("<h1>该会话已过期（可能是由于尝试以同一用户的身份进行多个并发登录）。</h1>");
			out.write("</body>");
			out.write("</html>");
		}
	}

}
