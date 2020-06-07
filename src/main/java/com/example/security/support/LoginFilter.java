package com.example.security.support;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.kaptcha.Constants;

/**
 * 登录过滤器：<br>
 * 1、包含用户名、密码、验证码。<br>
 * 2、支持表单、JSON。<br>
 * 
 * @author home
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
	private static final Logger log = LoggerFactory.getLogger(LoginFilter.class);
	private String captchaParameter = "captcha";
	@Resource
	private MessageSource messageSource;
	@Resource
	private ObjectMapper objectMapper;

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
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		if (MediaType.APPLICATION_JSON_UTF8_VALUE.contains(request.getContentType())) {
			Map<String, String> postData = Collections.emptyMap();
			try {
				postData = this.objectMapper.readValue(request.getInputStream(), Map.class);
			} catch (IOException e) {
				log.error(e.getLocalizedMessage(), e);
			}
			String requestCaptcha = postData.get(this.captchaParameter);
			this.attemptCaptcha(requestCaptcha, sessionCaptcha, session);
			String username = postData.get(super.getUsernameParameter());
			String password = postData.get(super.getPasswordParameter());
			username = username == null ? "" : username.trim();
			password = password == null ? "" : password;
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
					password);
			super.setDetails(request, authRequest);
			return super.getAuthenticationManager().authenticate(authRequest);
		} else {
			String requestCaptcha = this.obtainCaptcha(request);
			this.attemptCaptcha(requestCaptcha, sessionCaptcha, session);
			return super.attemptAuthentication(request, response);
		}
	}

	private void attemptCaptcha(String requestCaptcha, String sessionCaptcha, HttpSession session) {
		if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)
				|| !requestCaptcha.equalsIgnoreCase(sessionCaptcha)) {
			AuthenticationServiceException exception = new AuthenticationServiceException(
					this.messageSource.getMessage("LoginFilter.notMatched", null, LocaleContextHolder.getLocale()));
			session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
			throw exception;
		}
	}

	public final String getCaptchaParameter() {
		return captchaParameter;
	}

	public void setCaptchaParameter(String captchaParameter) {
		this.captchaParameter = captchaParameter;
	}

	@Nullable
	protected String obtainCaptcha(HttpServletRequest request) {
		return request.getParameter(captchaParameter);
	}
}
