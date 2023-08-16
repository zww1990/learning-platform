package com.example.security.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.security.model.UserToken;
import com.example.security.repository.UserTokenRepository;

/**
 * 用于存储用户的持久登录令牌的实现。
 * 
 * @author home
 */
@Transactional
public class UserTokenService implements PersistentTokenRepository {
	@Resource
	private UserTokenRepository tokenRepository;

	public UserTokenService() {
		super();
	}

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		UserToken user = new UserToken(token.getSeries(), token.getUsername(), token.getTokenValue(), token.getDate());
		this.tokenRepository.save(user);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		this.tokenRepository.findById(series).ifPresent(c -> {
			c.setLastUsed(lastUsed);
			c.setToken(tokenValue);
		});
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return this.tokenRepository.findById(seriesId)
				.map(m -> new PersistentRememberMeToken(m.getUsername(), m.getSeries(), m.getToken(), m.getLastUsed()))
				.orElse(null);
	}

	@Override
	public void removeUserTokens(String username) {
		this.tokenRepository.deleteByUsername(username);
	}

}
