package com.example.security.support;

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

	public CaptchaAuthenticationProvider() {
		super();
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		if (!((CaptchaAuthenticationDetails) authentication.getDetails()).isCheckPassed()) {
			throw new AuthenticationServiceException(
					super.messages.getMessage("CaptchaAuthenticationProvider.notMatched"));
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}

}
