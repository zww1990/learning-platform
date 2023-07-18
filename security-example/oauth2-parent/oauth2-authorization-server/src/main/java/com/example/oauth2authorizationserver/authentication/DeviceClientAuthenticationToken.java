package com.example.oauth2authorizationserver.authentication;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * 设备客户端身份验证令牌
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:48:34
 */
@SuppressWarnings("serial")
@Transient
public class DeviceClientAuthenticationToken extends OAuth2ClientAuthenticationToken {

	public DeviceClientAuthenticationToken(String clientId, ClientAuthenticationMethod clientAuthenticationMethod,
			@Nullable Object credentials, @Nullable Map<String, Object> additionalParameters) {
		super(clientId, clientAuthenticationMethod, credentials, additionalParameters);
	}

	public DeviceClientAuthenticationToken(RegisteredClient registeredClient,
			ClientAuthenticationMethod clientAuthenticationMethod, @Nullable Object credentials) {
		super(registeredClient, clientAuthenticationMethod, credentials);
	}

}
