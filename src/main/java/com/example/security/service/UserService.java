package com.example.security.service;

import java.util.stream.Stream;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.repository.UserRepository;

/**
 * 加载用户特定数据的核心接口。<br>
 * 它在整个框架中都用作用户DAO，并且是DaoAuthenticationProvider使用的策略。<br>
 * 该接口仅需要一种只读方法，从而简化了对新数据访问策略的支持。
 * 
 * @author home
 */
@Service
public class UserService implements UserDetailsService {
	@Resource
	private UserRepository userRepository;
	@Resource
	private MessageSource messageSource;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userRepository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(this.messageSource.getMessage("UserService.notFound",
						Stream.of(username).toArray(String[]::new), LocaleContextHolder.getLocale())));
	}

}
