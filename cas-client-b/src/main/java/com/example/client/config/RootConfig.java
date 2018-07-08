package com.example.client.config;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
@Import({ CasProperties.class, RootConfig.CasAutoConfig.class })
public class RootConfig {
	@Configuration
	@Conditional(CasCondition.class)
	public static class CasAutoConfig {
		/**
		 * @param props
		 * @return 实现单点登出协议。 它处理注册会话并销毁会话。
		 */
		@Bean
		public SingleSignOutFilter singleSignOutFilter(CasProperties props) {
			SingleSignOutFilter filter = new SingleSignOutFilter();
			filter.setCasServerUrlPrefix(props.getCasServerUrlPrefix());// CAS服务器的前缀url。
			return filter;
		}

		/**
		 * @param props
		 * @return 是来检测用户是否需要被认证。 如果用户需要认证，则会将用户重定向到CAS服务器。
		 */
		@Bean
		public AuthenticationFilter authenticationFilter(CasProperties props) {
			AuthenticationFilter filter = new AuthenticationFilter();
			filter.setCasServerLoginUrl(props.getCasServerLoginUrl());// 定义CAS服务器登录URL的位置
			filter.setServerName(props.getCasClientUrl());// 要发送到CAS服务器的服务URL
			return filter;
		}

		/**
		 * @param props
		 * @return 使用CAS 3.0协议验证票据。
		 */
		@Bean
		public Cas30ProxyReceivingTicketValidationFilter ticketValidationFilter(CasProperties props) {
			Cas30ProxyReceivingTicketValidationFilter filter = new Cas30ProxyReceivingTicketValidationFilter();
			filter.setServerName(props.getCasClientUrl());// 该应用程序托管的服务器的名称。 将使用此动态构建服务URL
			filter.setTicketValidator(new Cas30ServiceTicketValidator(props.getCasServerUrlPrefix()));// 将用来验证票据的票据验证器。
			return filter;
		}
	}

	public static class CasCondition implements Condition {
		public static final String CAS_MODE = "prod";

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return CAS_MODE.equalsIgnoreCase(context.getEnvironment().getProperty("cas.mode"));
		}

	}
}
