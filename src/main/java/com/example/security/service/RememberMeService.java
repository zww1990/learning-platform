package com.example.security.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import com.example.security.model.RememberMe;
import com.example.security.repository.RememberMeRepository;

/**
 * 用于存储用户的持久登录令牌的实现。
 * 
 * @author home
 */
@Service
@Transactional
public class RememberMeService implements PersistentTokenRepository {
	@Resource
	private RememberMeRepository rememberMeRepository;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		RememberMe me = new RememberMe(token.getSeries(), token.getUsername(), token.getTokenValue(), token.getDate());
		this.rememberMeRepository.save(me);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		this.rememberMeRepository.findById(series).ifPresent(c -> {
			c.setLastUsed(lastUsed);
			c.setToken(tokenValue);
		});
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		return this.rememberMeRepository.findById(seriesId)
				.map(m -> new PersistentRememberMeToken(m.getUsername(), m.getSeries(), m.getToken(), m.getLastUsed()))
				.orElse(null);
	}

	@Override
	public void removeUserTokens(String username) {
		this.rememberMeRepository.deleteByUsername(username);
	}

}
