package com.example.oauth2authorizationserver.federation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * 用户存储库OAuth2用户处理器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午8:03:38
 */
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

	private static final Logger log = LoggerFactory.getLogger(UserRepositoryOAuth2UserHandler.class);
	private final UserRepository userRepository = new UserRepository();

	@Override
	public void accept(OAuth2User user) {
		// 首次身份验证时在本地数据存储中捕获用户
		if (this.userRepository.findByName(user.getName()) == null) {
			log.info("Saving first-time user: name={}, claims={}, authorities={}", user.getName(), user.getAttributes(),
					user.getAuthorities());
			this.userRepository.save(user);
		}
	}

	static class UserRepository {

		private final Map<String, OAuth2User> userCache = new ConcurrentHashMap<>();

		public OAuth2User findByName(String name) {
			return this.userCache.get(name);
		}

		public void save(OAuth2User oauth2User) {
			this.userCache.put(oauth2User.getName(), oauth2User);
		}

	}

}
