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

/**
 * 验证码过滤器
 * 
 * @author home
 */
public class VerificationCodeFilter extends GenericFilterBean {
	private String verifycodeParameter = "verifycode";
	private String filterProcessesUrl = "/login";
	@Resource
	private MessageSource messageSource;

	public VerificationCodeFilter() {
	}

	public VerificationCodeFilter(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		if (request.getMethod().equals(RequestMethod.POST.name())
				&& this.filterProcessesUrl.equals(request.getServletPath())) {
			String requestVerifycode = this.obtainVerifycode(request);
			HttpSession session = request.getSession();
			String sessionVerifycode = (String) session.getAttribute(VerificationCode.VERIFICATION_CODE_KEY);
			if (StringUtils.isEmpty(requestVerifycode) || StringUtils.isEmpty(sessionVerifycode)
					|| !requestVerifycode.equalsIgnoreCase(sessionVerifycode)) {
				AuthenticationServiceException exception = new AuthenticationServiceException(this.messageSource
						.getMessage("VerificationCodeFilter.noMatching", null, LocaleContextHolder.getLocale()));
				session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, exception);
				throw exception;
			}
		}
		chain.doFilter(req, res);
	}

	public final String getVerifycodeParameter() {
		return verifycodeParameter;
	}

	public void setVerifycodeParameter(String verifycodeParameter) {
		this.verifycodeParameter = verifycodeParameter;
	}

	@Nullable
	protected String obtainVerifycode(HttpServletRequest request) {
		return request.getParameter(verifycodeParameter);
	}
}
