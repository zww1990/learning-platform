package com.example.security.support;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.code.kaptcha.Constants;

/**
 * 验证码认证提供者
 * 
 * @author home
 */
public class CaptchaAuthenticationProvider extends DaoAuthenticationProvider {
	private String captchaParameter = "captcha";

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		HttpSession session = request.getSession();
		String requestCaptcha = this.obtainCaptcha(request);
		String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		this.attemptCaptcha(requestCaptcha, sessionCaptcha, session);
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

	private void attemptCaptcha(String requestCaptcha, String sessionCaptcha, HttpSession session) {
		if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)
				|| !requestCaptcha.equalsIgnoreCase(sessionCaptcha)) {
			AuthenticationServiceException exception = new AuthenticationServiceException(
					super.messages.getMessage("CaptchaAuthenticationProvider.notMatched"));
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
