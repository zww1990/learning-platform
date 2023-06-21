package com.example.adminserver.config;

import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

/**
 * SecuritySecureConfig
 * 
 * @author weiwei
 * @version v1
 * @since 2022年1月27日,下午4:05:59
 */
@Configuration(proxyBeanMethods = false)
@EnableWebSecurity
public class SecuritySecureConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, AdminServerProperties adminServer) throws Exception {
		// 登录成功处理类
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(adminServer.path("/"));
		return http.authorizeHttpRequests(request -> request
				// 静态文件允许访问
				.requestMatchers(adminServer.path("/assets/**")).permitAll()
				.requestMatchers(adminServer.path("/actuator/info")).permitAll()
				.requestMatchers(adminServer.path("/actuator/health")).permitAll()
				// 登录页面允许访问
				.requestMatchers(adminServer.path("/login")).permitAll()
				// 其他所有请求需要登录
				.anyRequest().authenticated())
				// 登录页面配置，用于替换security默认页面
				.formLogin(login -> login.loginPage(adminServer.path("/login")).successHandler(successHandler))
				// 登出页面配置，用于替换security默认页面
				.logout(logout -> logout.logoutUrl(adminServer.path("/logout")))
				//
				.httpBasic(Customizer.withDefaults())
				//
				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
						.ignoringRequestMatchers("/instances", "/instances/*", "/actuator/**"))
				//
				.rememberMe(me -> me.key(UUID.randomUUID().toString()).tokenValiditySeconds(14 * 24 * 60 * 60)).build();
	}

	@Bean
	UserDetailsService userDetailsService(SecurityProperties security) {
		return new InMemoryUserDetailsManager(User.withUsername(security.getUser().getName())
				.password("{noop}" + security.getUser().getPassword()).roles("USER").build());
	}
}
