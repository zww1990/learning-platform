package com.example.springsecurityrestapi.authprovider;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.example.springsecurityrestapi.config.ApiAuthProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求标头身份验证提供者
 * 
 * @author zhang weiwei
 * @since 2023年7月19日,下午1:44:51
 */
@Component
@Slf4j
public class RequestHeaderAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private ApiAuthProperties apiAuth;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Object principal = authentication.getPrincipal();
		if (Objects.isNull(principal) || !principal.toString().equals(apiAuth.getHeaderValue())) {
			log.error("错误的请求标头凭据: {}", principal);
			throw new BadCredentialsException("错误的请求标头凭据");
		}
		return new PreAuthenticatedAuthenticationToken(principal, null, AuthorityUtils.NO_AUTHORITIES);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(PreAuthenticatedAuthenticationToken.class);
	}

}
