package com.example.security.support;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.security.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 登录过滤器：<br>
 * 1、包含用户名、密码、验证码。<br>
 * 2、支持表单、JSON。<br>
 * 
 * @author home
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	@Resource
	private MessageSource messageSource;
	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private SessionRegistry sessionRegistry;

	public LoginFilter() {
		super();
	}

	@Override
	@SuppressWarnings({ "deprecation", "unchecked" })
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (!request.getMethod().equals(RequestMethod.POST.name())) {
			throw new AuthenticationServiceException(this.messageSource.getMessage("LoginFilter.notSupported",
					Stream.of(request.getMethod()).toArray(String[]::new), LocaleContextHolder.getLocale()));
		}
		HttpSession session = request.getSession();
		if (MediaType.APPLICATION_JSON_UTF8_VALUE.contains(request.getContentType())) {
			Map<String, String> postData = Collections.emptyMap();
			try {
				postData = this.objectMapper.readValue(request.getInputStream(), Map.class);
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
			}
			String username = postData.get(super.getUsernameParameter());
			String password = postData.get(super.getPasswordParameter());
			username = username == null ? "" : username.trim();
			password = password == null ? "" : password;
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
					password);
			super.setDetails(request, authRequest);
			User principal = new User();
			principal.setUsername(username);
			this.sessionRegistry.registerNewSession(session.getId(), principal);
			return super.getAuthenticationManager().authenticate(authRequest);
		} else {
			return super.attemptAuthentication(request, response);
		}
	}

}
