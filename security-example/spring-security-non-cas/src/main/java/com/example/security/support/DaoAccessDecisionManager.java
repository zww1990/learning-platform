package com.example.security.support;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 做出最终访问控制（授权）决策。
 * 
 * @author home
 */
public class DaoAccessDecisionManager implements AccessDecisionManager {
	@Resource
	private MessageSource messageSource;

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		for (ConfigAttribute ca : configAttributes) {
			String attr = ca.getAttribute();
			if ("ROLE_LOGIN".equals(attr)) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					throw new AccessDeniedException(this.messageSource.getMessage(
							"DaoAccessDecisionManager.requiredLogin", null, LocaleContextHolder.getLocale()));
				}
				return;
			}
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority ga : authorities) {
				if (ga.getAuthority().equals(attr)) {
					return;
				}
			}
		}
		throw new AccessDeniedException(this.messageSource.getMessage("DaoAccessDecisionManager.accessDenied", null,
				LocaleContextHolder.getLocale()));
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
