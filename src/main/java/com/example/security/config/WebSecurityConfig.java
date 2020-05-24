package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()// 将内存认证添加到AuthenticationManagerBuilder中，并返回一个InMemoryUserDetailsManagerConfigurer来允许自定义内存认证。
				.withUser("admin")// 允许将用户添加到正在创建的UserDetailsManager。 可以多次调用此方法来添加多个用户。
				.password("admin")// 填充密码。 该属性是必需的。
				.roles("ADMIN")// 填充角色。 此方法是调用authorities(String)的快捷方式，但会自动为每个条目添加“ROLE_”。
				.and()// 返回方法链接的UserDetailsManagerConfigurer（即添加另一个用户）
				.withUser("guest")//
				.password("guest")//
				.roles("GUEST")//
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 允许基于HttpServletRequest使用限制访问
				.anyRequest()// 映射任何请求。
				.authenticated()// 指定任何经过身份验证的用户允许的URL。
				.and()// 使用SecurityConfigurer完成后返回SecurityBuilder。这对于方法链接很有用。
				.formLogin()// 指定支持基于表单的身份验证。 如果没有指定，将会生成一个默认的登录页面。
				.loginPage("/login")// 如果需要登录，指定发送用户的URL。 则在未指定此属性时将会生成默认登录页面。
				.permitAll()// 授予访问URL的权限为true
				.and()//
				.csrf()// 添加了CSRF支持。
				.disable()// 禁用CSRF
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true)// 控制对Spring Security的调试支持。
				.ignoring()// 允许添加Spring Security应该忽略的RequestMatcher实例。
							// Spring Security提供的Web Security（包括SecurityContext）在匹配的HttpServletRequest上将不可用。
							// 通常，注册的请求应该仅是静态资源的请求。 对于动态请求，请考虑映射请求以允许所有用户使用。
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**")//
		;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
