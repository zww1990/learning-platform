package com.example.oauth2authorizationserver.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.example.oauth2authorizationserver.federation.FederatedIdentityAuthenticationSuccessHandler;

/**
 * Spring安全框架配置
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:54:08
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class DefaultSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/assets/**", "/webjars/**", "/login", "/favicon.ico")
						.permitAll().anyRequest().authenticated())//
				.formLogin(formLogin -> formLogin.loginPage("/login"))//
				.oauth2Login(
						oauth2Login -> oauth2Login.loginPage("/login").successHandler(authenticationSuccessHandler()));

		return http.build();
	}

	private AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new FederatedIdentityAuthenticationSuccessHandler();
	}

	@Bean
	UserDetailsService users(DataSource dataSource) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		// 此处为了方便演示，初始化了一个用户
		UserDetails user = User.builder()//
				.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder()::encode)//
				.username("test")//
				.password("123456")//
				.roles("USER")//
				.build();
		manager.deleteUser(user.getUsername());
		manager.createUser(user);
		return manager;
	}

	@Bean
	SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

}
