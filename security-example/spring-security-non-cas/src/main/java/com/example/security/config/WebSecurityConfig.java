package com.example.security.config;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.UrlAuthorizationConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.LazyCsrfTokenRepository;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import com.example.security.service.UserService;
import com.example.security.service.UserTokenService;
import com.example.security.support.CaptchaFilter;
import com.example.security.support.DaoAccessDecisionManager;
import com.example.security.support.DaoFilterInvocationSecurityMetadataSource;
import com.example.security.support.JsonAuthenticationFailureHandler;
import com.example.security.support.JsonAuthenticationSuccessHandler;
import com.example.security.support.JsonSessionInformationExpiredStrategy;
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
@ConfigurationPropertiesScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Resource
	private FindByIndexNameSessionRepository<Session> sessionRepository;

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
		// 根据传递的自定义用户详细信息服务添加身份验证。然后返回Dao身份验证配置器，以允许自定义身份验证。
		auth.userDetailsService(this.userDetailsService());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.apply(new UrlAuthorizationConfigurer<>(http.getSharedObject(ApplicationContext.class)))
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setAccessDecisionManager(accessDecisionManager());
						object.setSecurityMetadataSource(securityMetadataSource());
						return object;
					}
				});
		http.addFilterAt(this.captchaFilter(), UsernamePasswordAuthenticationFilter.class)// 将过滤器添加到指定过滤器类的位置。
				.formLogin()// 指定支持基于表单的身份验证。 如果没有指定，将会生成一个默认的登录页面。
				.loginPage("/login")// 如果需要登录，指定发送用户的URL。 则在未指定此属性时将会生成默认登录页面。
				.and()//
				.csrf()// 添加了CSRF支持。
				.csrfTokenRepository(new LazyCsrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))// 指定要使用的CsrfTokenRepository。
//				.disable()// 禁用CSRF
				.and()//
				.rememberMe()// 允许配置“记住我”身份验证。
				.key(this.getByInetAddress())// 设置密钥以识别为记住我身份验证而创建的令牌。 默认值是安全随机生成的密钥。
				.tokenRepository(this.userTokenService())// 指定要使用的PersistentTokenRepository实例。
				.tokenValiditySeconds(60 * 60 * 24)// 允许指定令牌有效的时间（以秒为单位）
				.and()//
				.sessionManagement()// 允许配置会话管理。
				.maximumSessions(1)// 控制用户的最大会话数。 默认值为允许任意数量的用户。
				.expiredSessionStrategy(this.sessionInformationExpiredStrategy())// 确定检测到过期会话时的行为。
				.sessionRegistry(this.sessionRegistry())// 控制使用的SessionRegistry实现。
		;
		// 将过滤器添加到指定过滤器类的位置。
//		http.addFilterAt(new ConcurrentSessionFilter(this.sessionRegistry(),
//				this.sessionInformationExpiredStrategy()), ConcurrentSessionFilter.class);
//		http.addFilterAt(this.loginFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(false)// 控制对Spring Security的调试支持。
				.ignoring()// 允许添加Spring Security应该忽略的RequestMatcher实例。
				.antMatchers("/css/**", "/fonts/**", "/images/**", "/js/**", "/captcha");
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new UserService();
	}

	/**
	 * @return 用于编码密码的服务接口。首选实现是BCryptPasswordEncoder。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * @return 用于存储用户的持久登录令牌的实现。
	 */
	@Bean
	public UserTokenService userTokenService() {
		return new UserTokenService();
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
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
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

	/**
	 * @return 安全元数据源实现标记接口，旨在执行过滤器调用上键对的查找。
	 */
	@Bean
	public FilterInvocationSecurityMetadataSource securityMetadataSource() {
		return new DaoFilterInvocationSecurityMetadataSource();
	}

	/**
	 * @return 做出最终访问控制（授权）决策。
	 */
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		return new DaoAccessDecisionManager();
	}

	/**
	 * @return 验证码过滤器
	 */
	@Bean
	public CaptchaFilter captchaFilter() {
		return new CaptchaFilter();
	}
}
