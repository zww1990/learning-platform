package com.example.security.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

/**
 * 验证码身份验证详细信息源
 * 
 * @author home
 */
public class CaptchaAuthenticationDetailsSource
		implements AuthenticationDetailsSource<HttpServletRequest, CaptchaAuthenticationDetails> {

	public CaptchaAuthenticationDetailsSource() {
		super();
	}

	@Override
	public CaptchaAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new CaptchaAuthenticationDetails(context);
	}

}
