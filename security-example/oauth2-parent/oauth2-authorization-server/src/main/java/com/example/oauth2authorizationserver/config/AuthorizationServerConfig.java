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
		 * This sample demonstrates the use of a public client that does not store
		 * credentials or authenticate with the authorization server.
		 *
		 * The following components show how to customize the authorization server to
		 * allow for device clients to perform requests to the OAuth 2.0 Device
		 * Authorization Endpoint and Token Endpoint without a clientId/clientSecret.
		 *
		 * CAUTION: These endpoints will not require any authentication, and can be
		 * accessed by any client that has a valid clientId.
		 *
		 * It is therefore RECOMMENDED to carefully monitor the use of these endpoints
		 * and employ any additional protections as needed, which is outside the scope
		 * of this sample.
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

		// Save registered client's in db as if in-memory
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
		// Will be used by the ConsentController
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