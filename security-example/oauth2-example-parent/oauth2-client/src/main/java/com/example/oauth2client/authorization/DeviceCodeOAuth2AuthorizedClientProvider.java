package com.example.oauth2client.authorization;

import java.time.Clock;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.oauth2.client.ClientAuthorizationException;
import org.springframework.security.oauth2.client.OAuth2AuthorizationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 设备码OAuth2授权客户端提供者
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午6:35:02
 */
public final class DeviceCodeOAuth2AuthorizedClientProvider implements OAuth2AuthorizedClientProvider {

	private OAuth2AccessTokenResponseClient<OAuth2DeviceGrantRequest> accessTokenResponseClient = new OAuth2DeviceAccessTokenResponseClient();

	private Duration clockSkew = Duration.ofSeconds(60);

	private Clock clock = Clock.systemUTC();

	public void setAccessTokenResponseClient(
			OAuth2AccessTokenResponseClient<OAuth2DeviceGrantRequest> accessTokenResponseClient) {
		this.accessTokenResponseClient = accessTokenResponseClient;
	}

	public void setClockSkew(Duration clockSkew) {
		this.clockSkew = clockSkew;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	@Override
	public OAuth2AuthorizedClient authorize(OAuth2AuthorizationContext context) {
		Assert.notNull(context, "context cannot be null");
		ClientRegistration clientRegistration = context.getClientRegistration();
		if (!AuthorizationGrantType.DEVICE_CODE.equals(clientRegistration.getAuthorizationGrantType())) {
			return null;
		}
		OAuth2AuthorizedClient authorizedClient = context.getAuthorizedClient();
		if (authorizedClient != null && !hasTokenExpired(authorizedClient.getAccessToken())) {
			// 如果客户端已被授权，但访问令牌未过期，则不需要重新授权
			return null;
		}
		if (authorizedClient != null && authorizedClient.getRefreshToken() != null) {
			// 如果客户端已被授权，但访问令牌已过期，且刷新令牌可用，则委托给refresh_token。
			return null;
		}
		String deviceCode = context.getAttribute(OAuth2ParameterNames.DEVICE_CODE);
		// 尝试授权客户端，这将反复失败，直到用户授予权限
		OAuth2DeviceGrantRequest deviceGrantRequest = new OAuth2DeviceGrantRequest(clientRegistration, deviceCode);
		OAuth2AccessTokenResponse tokenResponse = getTokenResponse(clientRegistration, deviceGrantRequest);
		return new OAuth2AuthorizedClient(clientRegistration, context.getPrincipal().getName(),
				tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
	}

	private OAuth2AccessTokenResponse getTokenResponse(ClientRegistration clientRegistration,
			OAuth2DeviceGrantRequest deviceGrantRequest) {
		try {
			return this.accessTokenResponseClient.getTokenResponse(deviceGrantRequest);
		} catch (OAuth2AuthorizationException ex) {
			throw new ClientAuthorizationException(ex.getError(), clientRegistration.getRegistrationId(), ex);
		}
	}

	private boolean hasTokenExpired(OAuth2Token token) {
		return this.clock.instant().isAfter(token.getExpiresAt().minus(this.clockSkew));
	}

	public static Function<OAuth2AuthorizeRequest, Map<String, Object>> deviceCodeContextAttributesMapper() {
		return (authorizeRequest) -> {
			HttpServletRequest request = authorizeRequest.getAttribute(HttpServletRequest.class.getName());
			Assert.notNull(request, "request cannot be null");

			// 从请求中获取设备代码
			String deviceCode = request.getParameter(OAuth2ParameterNames.DEVICE_CODE);
			return (deviceCode != null) ? Collections.singletonMap(OAuth2ParameterNames.DEVICE_CODE, deviceCode)
					: Collections.emptyMap();
		};
	}

}
