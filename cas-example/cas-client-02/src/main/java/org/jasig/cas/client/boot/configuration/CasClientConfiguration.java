package org.jasig.cas.client.boot.configuration;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.authentication.Saml11AuthenticationFilter;
import org.jasig.cas.client.configuration.ConfigurationKeys;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.jasig.cas.client.validation.Saml11TicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Configuration
@EnableConfigurationProperties(CasClientConfigurationProperties.class)
public class CasClientConfiguration {

	@Autowired
	CasClientConfigurationProperties configProps;

	private CasClientConfigurer casClientConfigurer;

	private static Map<String, String> constructInitParams(final String casUrlParamName, final String casUrlParamVal,
			final String clientHostUrlVal) {
		final Map<String, String> initParams = new HashMap<>(2);
		initParams.put(casUrlParamName, casUrlParamVal);
		initParams.put(ConfigurationKeys.SERVER_NAME.getName(), clientHostUrlVal);
		return initParams;
	}

	private static void initFilter(final FilterRegistrationBean<Filter> filterRegistrationBean,
			final Filter targetFilter, final int filterOrder, final Map<String, String> initParams,
			final List<String> urlPatterns) {

		filterRegistrationBean.setFilter(targetFilter);
		filterRegistrationBean.setOrder(filterOrder);
		filterRegistrationBean.setInitParameters(initParams);
		if (!urlPatterns.isEmpty()) {
			filterRegistrationBean.setUrlPatterns(urlPatterns);
		}
	}

	@Bean
	@ConditionalOnProperty(prefix = "cas", name = "skipTicketValidation", havingValue = "false", matchIfMissing = true)
	public FilterRegistrationBean<Filter> casValidationFilter() {
		final FilterRegistrationBean<Filter> validationFilter = new FilterRegistrationBean<>();
		final Filter targetCasValidationFilter;
		switch (this.configProps.getValidationType()) {
		case CAS:
			targetCasValidationFilter = new Cas20ProxyReceivingTicketValidationFilter();
			break;
		case SAML:
			targetCasValidationFilter = new Saml11TicketValidationFilter();
			break;
		case CAS3:
		default:
			targetCasValidationFilter = new Cas30ProxyReceivingTicketValidationFilter();
			break;
		}

		initFilter(validationFilter, targetCasValidationFilter, 2,
				constructInitParams(ConfigurationKeys.CAS_SERVER_URL_PREFIX.getName(),
						this.configProps.getServerUrlPrefix(), this.configProps.getClientHostUrl()),
				this.configProps.getValidationUrlPatterns());

		if (this.configProps.getUseSession() != null) {
			validationFilter.getInitParameters().put(ConfigurationKeys.USE_SESSION.getName(),
					String.valueOf(this.configProps.getUseSession()));
		}
		if (this.configProps.getRedirectAfterValidation() != null) {
			validationFilter.getInitParameters().put(ConfigurationKeys.REDIRECT_AFTER_VALIDATION.getName(),
					String.valueOf(this.configProps.getRedirectAfterValidation()));
		}

		// Proxy tickets validation
		if (this.configProps.getAcceptAnyProxy() != null) {
			validationFilter.getInitParameters().put(ConfigurationKeys.ACCEPT_ANY_PROXY.getName(),
					String.valueOf(this.configProps.getAcceptAnyProxy()));
		}
		if (!this.configProps.getAllowedProxyChains().isEmpty()) {
			validationFilter.getInitParameters().put(ConfigurationKeys.ALLOWED_PROXY_CHAINS.getName(),
					StringUtils.collectionToDelimitedString(this.configProps.getAllowedProxyChains(), " "));
		}
		if (this.configProps.getProxyCallbackUrl() != null) {
			validationFilter.getInitParameters().put(ConfigurationKeys.PROXY_CALLBACK_URL.getName(),
					this.configProps.getProxyCallbackUrl());
		}
		if (this.configProps.getProxyReceptorUrl() != null) {
			validationFilter.getInitParameters().put(ConfigurationKeys.PROXY_RECEPTOR_URL.getName(),
					this.configProps.getProxyReceptorUrl());
		}

		if (this.casClientConfigurer != null) {
			this.casClientConfigurer.configureValidationFilter(validationFilter);
		}
		return validationFilter;
	}

	@Bean
	public FilterRegistrationBean<Filter> casAuthenticationFilter() {
		final FilterRegistrationBean<Filter> authnFilter = new FilterRegistrationBean<>();
		final Filter targetCasAuthnFilter = this.configProps.getValidationType() == EnableCasClient.ValidationType.CAS
				|| configProps.getValidationType() == EnableCasClient.ValidationType.CAS3 ? new AuthenticationFilter()
						: new Saml11AuthenticationFilter();

		initFilter(authnFilter, targetCasAuthnFilter, 3,
				constructInitParams(ConfigurationKeys.CAS_SERVER_LOGIN_URL.getName(),
						this.configProps.getServerLoginUrl(), this.configProps.getClientHostUrl()),
				this.configProps.getAuthenticationUrlPatterns());

		if (this.configProps.getGateway() != null) {
			authnFilter.getInitParameters().put(ConfigurationKeys.GATEWAY.getName(),
					String.valueOf(this.configProps.getGateway()));
		}

		if (this.casClientConfigurer != null) {
			this.casClientConfigurer.configureAuthenticationFilter(authnFilter);
		}
		return authnFilter;
	}

	@Bean
	public FilterRegistrationBean<Filter> casHttpServletRequestWrapperFilter() {
		final FilterRegistrationBean<Filter> reqWrapperFilter = new FilterRegistrationBean<>();
		reqWrapperFilter.setFilter(new HttpServletRequestWrapperFilter());
		if (!this.configProps.getRequestWrapperUrlPatterns().isEmpty()) {
			reqWrapperFilter.setUrlPatterns(this.configProps.getRequestWrapperUrlPatterns());
		}
		reqWrapperFilter.setOrder(4);

		if (this.casClientConfigurer != null) {
			this.casClientConfigurer.configureHttpServletRequestWrapperFilter(reqWrapperFilter);
		}
		return reqWrapperFilter;
	}

	@Bean
	public FilterRegistrationBean<Filter> casAssertionThreadLocalFilter() {
		final FilterRegistrationBean<Filter> assertionTLFilter = new FilterRegistrationBean<>();
		assertionTLFilter.setFilter(new AssertionThreadLocalFilter());
		if (!this.configProps.getAssertionThreadLocalUrlPatterns().isEmpty()) {
			assertionTLFilter.setUrlPatterns(this.configProps.getAssertionThreadLocalUrlPatterns());
		}
		assertionTLFilter.setOrder(5);

		if (this.casClientConfigurer != null) {
			this.casClientConfigurer.configureAssertionThreadLocalFilter(assertionTLFilter);
		}
		return assertionTLFilter;
	}

	@Autowired(required = false)
	void setConfigurers(final Collection<CasClientConfigurer> configurers) {
		if (CollectionUtils.isEmpty(configurers)) {
			return;
		}
		if (configurers.size() > 1) {
			throw new IllegalStateException(configurers.size() + " implementations of "
					+ "CasClientConfigurer were found when only 1 was expected. "
					+ "Refactor the configuration such that CasClientConfigurer is "
					+ "implemented only once or not at all.");
		}
		this.casClientConfigurer = configurers.iterator().next();
	}

	/**
	 * @return 侦听器，用于检测HTTP会话何时被销毁，并将其从管理会话的映射中删除。 <br>
	 *         还允许程序性地删除会话。启用CAS单一登出功能。
	 */
	@Bean
	public ServletListenerRegistrationBean<HttpSessionListener> casSingleSignOutHttpSessionListener() {
		ServletListenerRegistrationBean<HttpSessionListener> bean = new ServletListenerRegistrationBean<>();
		bean.setListener(new SingleSignOutHttpSessionListener());
		bean.setOrder(1);
		return bean;
	}

	/**
	 * @return 实现单点登出协议。 它处理注册会话并销毁会话。
	 */
	@Bean
	public FilterRegistrationBean<Filter> casSingleSignOutFilter() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new SingleSignOutFilter());
		bean.setOrder(1);
		if (!this.configProps.getSingleSignOutUrlPatterns().isEmpty()) {
			bean.setUrlPatterns(this.configProps.getSingleSignOutUrlPatterns());
		}
		Map<String, String> params = new HashMap<>(1);
		// CAS服务器的前缀url。
		params.put(ConfigurationKeys.CAS_SERVER_URL_PREFIX.getName(), this.configProps.getServerUrlPrefix());
		bean.setInitParameters(params);
		return bean;
	}
}
