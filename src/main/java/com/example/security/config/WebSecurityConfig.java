package com.example.security.config;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.example.security.service.RememberMeService;
import com.example.security.service.UserService;
import com.example.security.support.CaptchaAuthenticationDetailsSource;
import com.example.security.support.CaptchaAuthenticationProvider;
import com.example.security.support.JsonAuthenticationFailureHandler;
import com.example.security.support.JsonAuthenticationSuccessHandler;
import com.example.security.support.JsonSessionInformationExpiredStrategy;
import com.example.security.support.KaptchaProperties;
import com.example.security.support.LoginFilter;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * Spring Security配置类
 * 
 * @author home
 */
@Configuration
@EnableConfigurationProperties(KaptchaProperties.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
		auth.userDetailsService(this.userDetailsService())// 根据传入的自定义UserDetailsService添加身份验证。
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
				.authenticationDetailsSource(new CaptchaAuthenticationDetailsSource())// 指定自定义AuthenticationDetailsSource。
				.permitAll()// 授予访问URL的权限为true
				.and()//
				.csrf()// 添加了CSRF支持。
				.disable()// 禁用CSRF
				.rememberMe()// 允许配置“记住我”身份验证。
				.key(this.getByInetAddress())// 设置密钥以识别为记住我身份验证而创建的令牌。 默认值是安全随机生成的密钥。
				.tokenRepository(this.rememberMeService())// 指定要使用的PersistentTokenRepository实例。
				.tokenValiditySeconds(60 * 60 * 24)// 允许指定令牌有效的时间（以秒为单位）
				.and()//
				.sessionManagement()// 允许配置会话管理。
				.maximumSessions(1)// 控制用户的最大会话数。 默认值为允许任意数量的用户。
				.expiredSessionStrategy(this.sessionInformationExpiredStrategy())// 确定检测到过期会话时的行为。
		;
		// 将过滤器添加到指定过滤器类的位置。
//		http.addFilterAt(new ConcurrentSessionFilter(this.sessionRegistry(),
//				this.sessionInformationExpiredStrategy()), ConcurrentSessionFilter.class);
//		http.addFilterAt(this.loginFilter(), UsernamePasswordAuthenticationFilter.class);
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
						, "/captcha"// 验证码
				);
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
	public AuthenticationProvider authenticationProvider() {
		CaptchaAuthenticationProvider provider = new CaptchaAuthenticationProvider();
		provider.setPasswordEncoder(this.passwordEncoder());
		provider.setUserDetailsService(this.userDetailsService());
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
	 * @return 用于存储用户的持久登录令牌的实现。
	 */
	@Bean
	public RememberMeService rememberMeService() {
		return new RememberMeService();
	}

	/**
	 * @return 会话信息过期策略，它将错误消息写入响应正文。
	 */
	@Bean
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
		return new JsonSessionInformationExpiredStrategy();
	}

	/**
	 * @return 登录过滤器
	 * @throws Exception
	 */
//	@Bean
	public LoginFilter loginFilter() throws Exception {
		LoginFilter filter = new LoginFilter();
		filter.setAuthenticationManager(super.authenticationManager());
		filter.setFilterProcessesUrl("/login");// 设置确定是否需要身份验证的URL
		filter.setAuthenticationSuccessHandler(this.authenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(this.authenticationFailureHandler());
		ConcurrentSessionControlAuthenticationStrategy sessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				this.sessionRegistry());
		sessionStrategy.setMaximumSessions(1);// 设置maxSessions属性。 默认值为1。对无限会话使用-1。
		filter.setSessionAuthenticationStrategy(sessionStrategy);
		return filter;
	}

	/**
	 * @return 用于处理成功的用户身份验证的策略。
	 */
//	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new JsonAuthenticationSuccessHandler();
	}

	/**
	 * @return 用于处理失败的身份验证尝试的策略。
	 */
//	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new JsonAuthenticationFailureHandler();
	}

	/**
	 * @return 维护SessionInformation实例的注册表。
	 */
//	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	/**
	 * @param kp
	 * @return 图片验证码
	 */
	@Bean
	public Producer defaultKaptcha(KaptchaProperties kp) {
		DefaultKaptcha kaptcha = new DefaultKaptcha();
		Properties props = new Properties();
		props.put(Constants.KAPTCHA_BORDER, kp.getBorder().getEnabled().name());
		props.put(Constants.KAPTCHA_BORDER_COLOR, kp.getBorder().getColor());
		props.put(Constants.KAPTCHA_BORDER_THICKNESS, kp.getBorder().getThickness());
		props.put(Constants.KAPTCHA_NOISE_COLOR, kp.getNoise().getColor());
		props.put(Constants.KAPTCHA_NOISE_IMPL, kp.getNoise().getImpl());
		props.put(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, kp.getObscurificator().getImpl());
		props.put(Constants.KAPTCHA_PRODUCER_IMPL, kp.getProducer().getImpl());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_IMPL, kp.getTextproducer().getImpl());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, kp.getTextproducer().getCharString());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, kp.getTextproducer().getCharLength());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, kp.getTextproducer().getCharSpace());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, kp.getTextproducer().getFont().getNames());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, kp.getTextproducer().getFont().getColor());
		props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, kp.getTextproducer().getFont().getSize());
		props.put(Constants.KAPTCHA_WORDRENDERER_IMPL, kp.getWord().getImpl());
		props.put(Constants.KAPTCHA_BACKGROUND_IMPL, kp.getBackground().getImpl());
		props.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, kp.getBackground().getClear().getFrom());
		props.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, kp.getBackground().getClear().getTo());
		props.put(Constants.KAPTCHA_IMAGE_WIDTH, kp.getImage().getWidth());
		props.put(Constants.KAPTCHA_IMAGE_HEIGHT, kp.getImage().getHeight());
		kaptcha.setConfig(new Config(props));
		return kaptcha;
	}

}
