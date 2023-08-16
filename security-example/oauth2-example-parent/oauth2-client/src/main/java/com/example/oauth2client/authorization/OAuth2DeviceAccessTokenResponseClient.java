package com.example.oauth2client.authorization;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

/**
 * OAuth2设备访问令牌响应客户端
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午6:36:35
 */
public final class OAuth2DeviceAccessTokenResponseClient
		implements OAuth2AccessTokenResponseClient<OAuth2DeviceGrantRequest> {

	private RestOperations restOperations;

	public OAuth2DeviceAccessTokenResponseClient() {
		RestTemplate restTemplate = new RestTemplate(
				Arrays.asList(new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter()));
		restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
		this.restOperations = restTemplate;
	}

	public void setRestOperations(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	@Override
	public OAuth2AccessTokenResponse getTokenResponse(OAuth2DeviceGrantRequest deviceGrantRequest) {
		ClientRegistration clientRegistration = deviceGrantRequest.getClientRegistration();

		HttpHeaders headers = new HttpHeaders();
		/*
		 * 此示例演示不存储凭据或不通过授权服务器进行身份验证的公共客户端的使用。
		 *
		 * 有关允许公共客户端的自定义示例，请参见授权服务器示例中的DeviceClientAuthenticationProvider。
		 *
		 * 对于机密客户端，将client-authentication-method更改为client_secret_basic，
		 * 并将client-secret设置为发送带有clientId/clientSecret的OAuth 2.0令牌请求。
		 */
		if (!clientRegistration.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
			headers.setBasicAuth(clientRegistration.getClientId(), clientRegistration.getClientSecret());
		}

		MultiValueMap<String, Object> requestParameters = new LinkedMultiValueMap<>();
		requestParameters.add(OAuth2ParameterNames.GRANT_TYPE, deviceGrantRequest.getGrantType().getValue());
		requestParameters.add(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
		requestParameters.add(OAuth2ParameterNames.DEVICE_CODE, deviceGrantRequest.getDeviceCode());

		RequestEntity<MultiValueMap<String, Object>> requestEntity = RequestEntity
				.post(deviceGrantRequest.getClientRegistration().getProviderDetails().getTokenUri()).headers(headers)
				.body(requestParameters);

		try {
			return this.restOperations.exchange(requestEntity, OAuth2AccessTokenResponse.class).getBody();
		} catch (RestClientException ex) {
			OAuth2Error oauth2Error = new OAuth2Error("invalid_token_response",
					"尝试检索OAuth 2.0访问令牌响应时出错: " + ex.getMessage(), null);
			throw new OAuth2AuthorizationException(oauth2Error, ex);
		}
	}

}
