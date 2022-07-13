package com.example.security.support;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.google.code.kaptcha.Constants;

/**
 * 验证码身份验证详细信息
 * 
 * @author home
 */
@SuppressWarnings("serial")
public class CaptchaAuthenticationDetails extends WebAuthenticationDetails {
	private String captchaParameter = "captcha";
	private boolean checkPassed;

	public CaptchaAuthenticationDetails(HttpServletRequest request) {
		super(request);
		String requestCaptcha = request.getParameter(captchaParameter);
		String sessionCaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		checkPassed = requestCaptcha != null && sessionCaptcha != null
				&& requestCaptcha.equalsIgnoreCase(sessionCaptcha);
	}

	public boolean isCheckPassed() {
		return checkPassed;
	}

}
