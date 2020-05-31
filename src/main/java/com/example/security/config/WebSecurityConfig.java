package com.example.security.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.service.UserService;

/**
 * Spring Security配置类
 * 
 * @author home
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService)// 根据传入的自定义UserDetailsService添加身份验证。
													// 然后，它返回DaoAuthenticationConfigurer以允许自定义身份验证。
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 允许基于HttpServletRequest使用限制访问
				.antMatchers("/admin/**")// 创建未指定HttpMethod的AntPathRequestMatcher实例列表。
				.hasRole("ADMIN")// 指定URL的快捷方式需要特定的角色。
				.antMatchers("/guest/**")//
				.hasRole("GUEST")//
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
				.antMatchers("/css/**"// 样式文件
						, "/fonts/**"// 字体文件
						, "/images/**"// 图片文件
						, "/js/**"// 脚本文件
						, "/verifycode"// 验证码
				);
	}

	/**
	 * @return 用于编码密码的服务接口。首选实现是BCryptPasswordEncoder。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * @return 角色层次结构的简单接口。
	 */
	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
		Map<String, List<String>> roleMap = new HashMap<>();
		roleMap.put("ROLE_ADMIN", Arrays.asList("ROLE_GUEST"));
		hierarchy.setHierarchy(RoleHierarchyUtils.roleHierarchyFromMap(roleMap));
		return hierarchy;
	}
}
