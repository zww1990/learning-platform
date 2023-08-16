package com.example.oauth2client.authorization;

import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

/**
 * OAuth2设备授权请求
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午6:43:10
 */
public final class OAuth2DeviceGrantRequest extends AbstractOAuth2AuthorizationGrantRequest {

	private final String deviceCode;

	public OAuth2DeviceGrantRequest(ClientRegistration clientRegistration, String deviceCode) {
		super(AuthorizationGrantType.DEVICE_CODE, clientRegistration);
		Assert.hasText(deviceCode, "deviceCode cannot be empty");
		this.deviceCode = deviceCode;
	}

	public String getDeviceCode() {
		return this.deviceCode;
	}

}
