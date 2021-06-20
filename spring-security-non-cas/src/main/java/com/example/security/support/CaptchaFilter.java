package com.example.security.support;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.web.WebAttributes;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import com.google.code.kaptcha.Constants;

/**
 * 验证码过滤器
 * 
 * @author home
 */
public class CaptchaFilter extends GenericFilterBean {
	private String captchaParameter = "captcha";
	private String filterProcessesUrl = "/login";
	@Resource
	private MessageSource messageSource;

	public CaptchaFilter() {
		super();
	}

	public CaptchaFilter(String filterProcessesUrl) {
		super();
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getMethod().equals(RequestMethod.POST.name())
				&& this.filterProcessesUrl.equals(request.getServletPath())) {
			String requestCaptcha = this.obtainCaptcha(request);
			HttpSession session = request.getSession();
			String sessionCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
			if (StringUtils.isEmpty(requestCaptcha) || StringUtils.isEmpty(sessionCaptcha)
					|| !requestCaptcha.equalsIgnoreCase(sessionCaptcha)) {
				AuthenticationServiceException exception = new AuthenticationServiceException(this.messageSource
						.getMessage("CaptchaFilter.notMatched", null, LocaleContextHolder.getLocale()));
				session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
				throw exception;
			}
		}
		chain.doFilter(req, res);
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
