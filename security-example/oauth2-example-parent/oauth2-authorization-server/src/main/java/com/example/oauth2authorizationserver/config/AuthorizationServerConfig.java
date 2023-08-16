package com.example.oauth2authorizationserver.config;

import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import com.example.oauth2authorizationserver.authentication.DeviceClientAuthenticationProvider;
import com.example.oauth2authorizationserver.federation.FederatedIdentityIdTokenCustomizer;
import com.example.oauth2authorizationserver.jose.Jwks;
import com.example.oauth2authorizationserver.web.authentication.DeviceClientAuthenticationConverter;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

/**
 * 授权服务配置
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:49:15
 */
@Configuration(proxyBeanMethods = false)
public class AuthorizationServerConfig {
	private static final String CUSTOM_CONSENT_PAGE_URI = "/oauth2/consent";

	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http,
			RegisteredClientRepository registeredClientRepository,
			AuthorizationServerSettings authorizationServerSettings) throws Exception {

		OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

		/*
		 * 此示例演示不存储凭据或不通过授权服务器进行身份验证的公共客户端的使用。
		 *
		 * 以下组件显示如何自定义授权服务器，以允许设备客户端在没有clientId/clientSecret的情况下执行对OAuth
		 * 2.0设备授权端点和令牌端点的请求。
		 *
		 * 注意事项：这些端点不需要任何身份验证，具有有效clientId的任何客户端都可以访问这些端点。
		 *
		 * 因此，建议仔细监视这些端点的使用，并根据需要使用任何额外的保护，这超出了本示例的范围。
		 */
		DeviceClientAuthenticationConverter deviceClientAuthenticationConverter = new DeviceClientAuthenticationConverter(
				authorizationServerSettings.getDeviceAuthorizationEndpoint());
		DeviceClientAuthenticationProvider deviceClientAuthenticationProvider = new DeviceClientAuthenticationProvider(
				registeredClientRepository);

		http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
				.deviceAuthorizationEndpoint(
						deviceAuthorizationEndpoint -> deviceAuthorizationEndpoint.verificationUri("/activate"))
				.deviceVerificationEndpoint(
						deviceVerificationEndpoint -> deviceVerificationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
				.clientAuthentication(clientAuthentication -> clientAuthentication
						.authenticationConverter(deviceClientAuthenticationConverter)
						.authenticationProvider(deviceClientAuthenticationProvider))
				.authorizationEndpoint(
						authorizationEndpoint -> authorizationEndpoint.consentPage(CUSTOM_CONSENT_PAGE_URI))
				.oidc(Customizer.withDefaults()); // Enable OpenID Connect 1.0

		http.exceptionHandling((exceptions) -> exceptions.defaultAuthenticationEntryPointFor(
				new LoginUrlAuthenticationEntryPoint("/login"), new MediaTypeRequestMatcher(MediaType.TEXT_HTML)))
				.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()));
		return http.build();
	}

	@Bean
	RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
		RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())//
				.clientId("messaging-client")//
				.clientSecret("{noop}secret")//
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)//
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)//
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)//
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)//
				.redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")//
				.redirectUri("http://127.0.0.1:8080/authorized")//
				.postLogoutRedirectUri("http://127.0.0.1:8080/logged-out")//
				.scope(OidcScopes.OPENID)//
				.scope(OidcScopes.PROFILE)//
				.scope("message.read")//
				.scope("message.write")//
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())//
				.build();

		RegisteredClient deviceClient = RegisteredClient.withId(UUID.randomUUID().toString())//
				.clientId("device-messaging-client")//
				.clientAuthenticationMethod(ClientAuthenticationMethod.NONE)//
				.authorizationGrantType(AuthorizationGrantType.DEVICE_CODE)//
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)//
				.scope("message.read")//
				.scope("message.write")//
				.build();

		// 将注册的客户端保存在数据库中，就像在内存中一样
		MyJdbcRegisteredClientRepository registeredClientRepository = new MyJdbcRegisteredClientRepository(
				jdbcTemplate);
		registeredClientRepository.save(registeredClient);
		registeredClientRepository.save(deviceClient);

		return registeredClientRepository;
	}

	@Bean
	OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository) {
		return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
	}

	@Bean
	OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate,
			RegisteredClientRepository registeredClientRepository) {
		// 将由同意控制器使用
		return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
	}

	@Bean
	OAuth2TokenCustomizer<JwtEncodingContext> idTokenCustomizer() {
		return new FederatedIdentityIdTokenCustomizer();
	}

	@Bean
	JWKSource<SecurityContext> jwkSource() {
		RSAKey rsaKey = Jwks.generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKey);
		return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
	}

	@Bean
	JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
		return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
	}

	@Bean
	AuthorizationServerSettings authorizationServerSettings() {
		return AuthorizationServerSettings.builder().build();
	}

}
