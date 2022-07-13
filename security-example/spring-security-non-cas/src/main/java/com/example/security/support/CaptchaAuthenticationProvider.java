package com.example.security.support;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 验证码认证提供者
 * 
 * @author home
 */
public class CaptchaAuthenticationProvider extends DaoAuthenticationProvider {
	@Resource
	private MessageSource messageSource;

	public CaptchaAuthenticationProvider() {
		super();
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!((CaptchaAuthenticationDetails) authentication.getDetails()).isCheckPassed()) {
			throw new AuthenticationServiceException(this.messageSource
					.getMessage("CaptchaAuthenticationProvider.notMatched", null, LocaleContextHolder.getLocale()));
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

}
