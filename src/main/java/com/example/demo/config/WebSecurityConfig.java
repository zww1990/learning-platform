package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//允许基于HttpServletRequest使用限制访问
				.antMatchers("/", "/home")//映射不关心使用哪个HttpMethod的AntPathRequestMatcher实例列表。
				.permitAll()//指定任何人都允许使用这些网址。
				.anyRequest()//映射任何请求。
				.authenticated()//指定任何经过身份验证的用户允许的URL。
				.and()//
				.formLogin()//指定支持基于表单的身份验证。 如果没有指定，将会生成一个默认的登录页面。
				.loginPage("/login")//如果需要登录，指定发送用户的URL。 则在未指定此属性时将会生成默认登录页面。
				.permitAll()//等价调用permitAll(true)
				.and()//完成后使用SecurityConfigurer返回SecurityBuilder。 这对方法链接很有用。
				.logout()//提供注销支持。 这在使用WebSecurityConfigurerAdapter时会自动应用。 
				//缺省情况下，访问URL“/logout”将通过使HTTP会话无效，清除已配置的任何rememberMe()身份验证，
				//清除SecurityContextHolder，然后重定向到“/login?success”来登录用户。
				.permitAll();//permitAll(boolean)作为参数true的快捷方式。
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()//将内存认证添加到AuthenticationManagerBuilder中，
				//并返回一个InMemoryUserDetailsManagerConfigurer来允许自定义内存认证。
				.withUser("test")//允许将用户添加到正在创建的UserDetailsManager。 可以多次调用此方法来添加多个用户。
				.password("1234")//填充密码。 该属性是必需的。
				.roles("USER")//填充角色。 此方法是调用authorities(String)的快捷方式，但会自动为每个条目添加“ROLE_”。
				//这意味着以下内容：builder.roles("USER","ADMIN"); 
				//相当于builder.authorities("ROLE_USER","ROLE_ADMIN");
				//该属性是必需的，但也可以使用authorities(String)填充。
				.and()//返回方法链接的UserDetailsManagerConfigurer（即添加另一个用户）
				.withUser("admin")//
				.password("1234")//
				.roles("USER", "ADMIN");//
	}
}
