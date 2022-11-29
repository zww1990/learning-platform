package com.example.adminserver.config;

import java.util.UUID;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

	private final AdminServerProperties adminServer;

	private final SecurityProperties security;

	public SecuritySecureConfig(AdminServerProperties adminServer, SecurityProperties security) {
		this.adminServer = adminServer;
		this.security = security;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 登录成功处理类
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setTargetUrlParameter("redirectTo");
		successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

		http.authorizeRequests()
				// 静态文件允许访问
				.antMatchers(this.adminServer.path("/assets/**")).permitAll()
				.antMatchers(this.adminServer.path("/actuator/info")).permitAll()
				.antMatchers(this.adminServer.path("/actuator/health")).permitAll()
				// 登录页面允许访问
				.antMatchers(this.adminServer.path("/login")).permitAll()
				// 其他所有请求需要登录
				.anyRequest().authenticated().and()
				// 登录页面配置，用于替换security默认页面
				.formLogin().loginPage(this.adminServer.path("/login")).successHandler(successHandler).and()
				// 登出页面配置，用于替换security默认页面
				.logout().logoutUrl(this.adminServer.path("/logout")).and()
				//
				.httpBasic().and()
				//
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers("/instances", "/instances/*", "/actuator/**").and()
				//
				.rememberMe().key(UUID.randomUUID().toString()).tokenValiditySeconds(14 * 24 * 60 * 60);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 需要为“记住功能”提供 UserDetailsService
		auth.inMemoryAuthentication().withUser(security.getUser().getName())
				.password("{noop}" + security.getUser().getPassword()).roles("USER");
	}

}
