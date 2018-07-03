package com.example.client.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "cas.mode", havingValue = "prod")
@EnableConfigurationProperties(CasProperties.class)
public class CasClientAutoConfig {
	/**
	 * @return 侦听器，用于检测HTTP会话何时被销毁，并将其从管理会话的映射中删除。 <br>
	 *         还允许程序性地删除会话。启用CAS单一登出功能。
	 */
	@Bean
	public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> bean = new ServletListenerRegistrationBean<>();
		bean.setListener(new SingleSignOutHttpSessionListener());
		bean.setOrder(1);
		return bean;
	}

	/**
	 * @param props
	 * @return 实现单点登出协议。 它处理注册会话并销毁会话。
	 */
	@Bean
	public FilterRegistrationBean singleSignOutFilter(CasProperties props) {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new SingleSignOutFilter());
		bean.setOrder(3);
		bean.addUrlPatterns("/*");
		bean.addInitParameter("casServerUrlPrefix", props.getServerUrlPrefix());// CAS服务器的前缀url。
		return bean;
	}

	/**
	 * @param props
	 * @return 是来检测用户是否需要被认证。 如果用户需要认证，则会将用户重定向到CAS服务器。
	 */
	@Bean
	public FilterRegistrationBean authenticationFilter(CasProperties props) {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new AuthenticationFilter());
		bean.setOrder(4);
		bean.addUrlPatterns("/*");
		bean.addInitParameter("casServerLoginUrl", props.getServerLoginUrl());// 定义CAS服务器登录URL的位置
		bean.addInitParameter("service", props.getClientUrl());// 要发送到CAS服务器的服务URL
		return bean;
	}

	/**
	 * @param props
	 * @return 使用CAS 3.0协议验证票据。
	 */
	@Bean
	public FilterRegistrationBean ticketValidationFilter(CasProperties props) {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		Cas30ProxyReceivingTicketValidationFilter filter = new Cas30ProxyReceivingTicketValidationFilter();
		filter.setTicketValidator(new Cas30ServiceTicketValidator(props.getServerUrlPrefix()));// 将用来验证票据的票据验证器。
		bean.setFilter(filter);
		bean.setOrder(5);
		bean.addUrlPatterns("/*");
		bean.addInitParameter("service", props.getClientUrl());// 该应用程序托管的服务器的名称。 将使用此动态构建服务URL
		bean.addInitParameter("casServerUrlPrefix", props.getServerUrlPrefix());// 将用来验证票据的票据验证器。
		return bean;
	}

	/**
	 * @return 包装一个HttpServletRequest，以便getRemoteUser和getPrincipal返回CAS相关的条目。
	 */
	@Bean
	public FilterRegistrationBean httpServletRequestWrapperFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new HttpServletRequestWrapperFilter());
		bean.setOrder(6);
		bean.addUrlPatterns("/*");
		return bean;
	}

	/**
	 * @return 将断言置于ThreadLocal中，以便其他资源可以访问不具有Web层会话的权限。
	 */
	@Bean
	public FilterRegistrationBean assertionThreadLocalFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new AssertionThreadLocalFilter());
		bean.setOrder(7);
		bean.addUrlPatterns("/*");
		return bean;
	}
}
