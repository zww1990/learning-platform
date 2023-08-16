package com.example.security.config;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas30ProxyTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import com.example.security.service.UserService;
import com.example.security.support.JsonSessionInformationExpiredStrategy;

/**
 * Spring Security配置类
 * 
 * @author home
 */
@Configuration
@ConfigurationPropertiesScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private FindByIndexNameSessionRepository<Session> sessionRepository;
	@Resource
	private CasProperties casProperties;

	/**
	 * @return 获取网卡MAC地址
	 * @throws Exception
	 */
	private String getByInetAddress() throws Exception {
		byte[] bytes = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (byte bt : bytes) {
			sb.append(Integer.toHexString(bt & 0xff)).append('-');
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 根据传递的自定义身份验证提供器添加身份验证。
		// 由于身份验证提供器实现未知，因此所有自定义必须在外部完成，并且立即返回身份验证管理器构建器。
		auth.authenticationProvider(this.authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()// 允许使用RequestMatcher实现（即通过URL模式）基于HttpServletRequest限制访问。
				.antMatchers("/admin/**").hasRole("ADMIN")// 指定URL的快捷方式需要特定的角色。
				.antMatchers("/guest/**").hasRole("GUEST")//
				.antMatchers("/login/cas").permitAll()// 指定任何人都可以允许URL。
				.anyRequest().authenticated()// 指定任何经过身份验证的用户允许的URL。
				.and()// 使用SecurityConfigurer完成后返回SecurityBuilder。这对于方法链接很有用。
				.exceptionHandling()// 允许配置异常处理。当使用 WebSecurityCongurerAdapter时，会自动应用此功能。
				.authenticationEntryPoint(this.authenticationEntryPoint())// 设置要使用的身份验证输入点。
				.and()//
				.csrf()// 添加了CSRF支持。
				.csrfTokenRepository(new LazyCsrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))// 指定要使用的CsrfTokenRepository。
				.and()//
				.logout().disable()// 禁用注销
				.addFilter(this.casAuthenticationFilter())// 添加过滤器
				.addFilterBefore(this.singleSignOutFilter(), CasAuthenticationFilter.class)// 允许在一个已知过滤器类之前添加过滤器。
				.addFilterAt(this.logoutFilter(), LogoutFilter.class)// 将过滤器添加到指定过滤器类的位置。
				.sessionManagement()// 允许配置会话管理。
				.maximumSessions(1)// 控制用户的最大会话数。 默认值为允许任意数量的用户。
				.expiredSessionStrategy(this.sessionInformationExpiredStrategy())// 确定检测到过期会话时的行为。
				.sessionRegistry(this.sessionRegistry())// 控制使用的SessionRegistry实现。
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true)// 控制对Spring Security的调试支持。
				.ignoring()// 允许添加Spring Security应该忽略的RequestMatcher实例。
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/captcha");
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return new ProviderManager(Arrays.asList(this.authenticationProvider()));
	}

	/**
	 * @return 验证码认证提供者
	 */
	@Bean
	public AuthenticationProvider authenticationProvider() throws Exception {
		CasAuthenticationProvider provider = new CasAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsService());
		provider.setServiceProperties(this.serviceProperties());
		provider.setTicketValidator(this.ticketValidator());
		provider.setKey(this.getByInetAddress());
		return provider;
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

	/**
	 * @return 会话信息过期策略，它将错误消息写入响应正文。
	 */
	@Bean
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new JsonSessionInformationExpiredStrategy();
	}

	/**
	 * @return 从Spring会话检索会话信息而不是维护其本身的会话注册表。这允许在群集环境中与Spring Security并发会话管理。
	 */
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
	}

	/**
	 * @return 存储与此CAS服务相关的属性。
	 */
	@Bean
	public ServiceProperties serviceProperties() {
		ServiceProperties service = new ServiceProperties();
		service.setService(this.casProperties.getClient().getLogin());
		return service;
	}

	/**
	 * @return 异常转换过滤器用于通过JA-SIG中央身份验证服务（CAS）开始身份验证。
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		CasAuthenticationEntryPoint point = new CasAuthenticationEntryPoint();
		point.setLoginUrl(this.casProperties.getServer().getLogin());
		point.setServiceProperties(this.serviceProperties());
		return point;
	}

	/**
	 * @return CAS协议v3的服务和代理票证验证服务。
	 */
	@Bean
	public TicketValidator ticketValidator() {
		return new Cas30ProxyTicketValidator(this.casProperties.getServer().getPrefix());
	}

	/**
	 * @return 处理CAS服务票证，获取代理授予票证，并处理代理票证。
	 * @throws Exception
	 */
	@Bean
	public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
		CasAuthenticationFilter filter = new CasAuthenticationFilter();
		filter.setServiceProperties(this.serviceProperties());
		filter.setAuthenticationManager(this.authenticationManager());
		return filter;
	}

	/**
	 * @return 实现单点注销协议。它处理注册会话和销毁会话。
	 */
	@Bean
	public SingleSignOutFilter singleSignOutFilter() {
		SingleSignOutFilter filter = new SingleSignOutFilter();
		filter.setIgnoreInitConfiguration(true);
		return filter;
	}

	/**
	 * @return 注销过滤器
	 */
	@Bean
	public LogoutFilter logoutFilter() {
		LogoutFilter filter = new LogoutFilter(this.casProperties.getServer().getLogout(),
				new SecurityContextLogoutHandler());
		filter.setFilterProcessesUrl(this.casProperties.getClient().getLogout());
		return filter;
	}
}
