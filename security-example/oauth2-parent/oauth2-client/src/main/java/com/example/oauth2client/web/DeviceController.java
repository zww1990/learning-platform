package com.example.oauth2client.web;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 设备控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:29:55
 */
@Controller
public class DeviceController {

	private static final Set<String> DEVICE_GRANT_ERRORS = new HashSet<>(
			Arrays.asList("authorization_pending", "slow_down", "access_denied", "expired_token"));

	private static final ParameterizedTypeReference<Map<String, Object>> TYPE_REFERENCE = new ParameterizedTypeReference<>() {
	};

	private final ClientRegistrationRepository clientRegistrationRepository;

	private final WebClient webClient;

	private final String messagesBaseUri;

	public DeviceController(ClientRegistrationRepository clientRegistrationRepository, WebClient webClient,
			@Value("${messages.base-uri}") String messagesBaseUri) {

		this.clientRegistrationRepository = clientRegistrationRepository;
		this.webClient = webClient;
		this.messagesBaseUri = messagesBaseUri;
	}

	@GetMapping("/device_authorize")
	public String authorize(Model model) {
		ClientRegistration clientRegistration = this.clientRegistrationRepository
				.findByRegistrationId("messaging-client-device-code");

		MultiValueMap<String, String> requestParameters = new LinkedMultiValueMap<>();
		requestParameters.add(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
		requestParameters.add(OAuth2ParameterNames.SCOPE,
				StringUtils.collectionToDelimitedString(clientRegistration.getScopes(), " "));

		String deviceAuthorizationUri = (String) clientRegistration.getProviderDetails().getConfigurationMetadata()
				.get("device_authorization_endpoint");

		Map<String, Object> responseParameters = this.webClient.post()//
				.uri(deviceAuthorizationUri)//
				.headers(headers -> {
					/*
					 * 此示例演示不存储凭据或不通过授权服务器进行身份验证的公共客户端的使用。
					 *
					 * 有关允许公共客户端的自定义示例，请参见授权服务器示例中的DeviceClientAuthenticationProvider。
					 *
					 * 对于机密客户端，请将client-authentication-method更改为client_secret_basic，
					 * 并将client-secret设置为使用clientId/clientSecret发送OAuth 2.0设备授权请求。
					 */
					if (!clientRegistration.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {
						headers.setBasicAuth(clientRegistration.getClientId(), clientRegistration.getClientSecret());
					}
				})//
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)//
				.body(BodyInserters.fromFormData(requestParameters))//
				.retrieve()//
				.bodyToMono(TYPE_REFERENCE)//
				.block();

		Objects.requireNonNull(responseParameters, "Device Authorization Response cannot be null");
		Instant issuedAt = Instant.now();
		Integer expiresIn = (Integer) responseParameters.get(OAuth2ParameterNames.EXPIRES_IN);
		Instant expiresAt = issuedAt.plusSeconds(expiresIn);

		model.addAttribute("deviceCode", responseParameters.get(OAuth2ParameterNames.DEVICE_CODE));
		model.addAttribute("expiresAt", expiresAt);
		model.addAttribute("userCode", responseParameters.get(OAuth2ParameterNames.USER_CODE));
		model.addAttribute("verificationUri", responseParameters.get(OAuth2ParameterNames.VERIFICATION_URI));
		// 备注：您可以使用一个QR代码来显示这个URL
		model.addAttribute("verificationUriComplete",
				responseParameters.get(OAuth2ParameterNames.VERIFICATION_URI_COMPLETE));
		// 跳转到设备授权页面
		return "device-authorize";
	}

	@PostMapping("/device_authorize")
	public ResponseEntity<Void> poll(@RequestParam(OAuth2ParameterNames.DEVICE_CODE) String deviceCode,
			@RegisteredOAuth2AuthorizedClient("messaging-client-device-code") OAuth2AuthorizedClient authorizedClient) {

		/*
		 * 客户端将重复轮询，直到授权被授予。
		 *
		 * OAuth2AuthorizedClientManager使用device_code参数发出令牌请求，该请求返回authorization_pending
		 * ，直到用户授予授权。
		 *
		 * 如果用户拒绝授权，则返回access_denied，轮询应该停止。
		 *
		 * 如果设备代码过期，将返回expired_token，轮询将停止。
		 *
		 * 当客户端被授权时，这个端点简单地返回200 OK。
		 */
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@ExceptionHandler(OAuth2AuthorizationException.class)
	public ResponseEntity<OAuth2Error> handleError(OAuth2AuthorizationException ex) {
		// 异常处理
		String errorCode = ex.getError().getErrorCode();
		if (DEVICE_GRANT_ERRORS.contains(errorCode)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getError());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getError());
	}

	@GetMapping("/device_authorized")
	public String authorized(Model model,
			@RegisteredOAuth2AuthorizedClient("messaging-client-device-code") OAuth2AuthorizedClient authorizedClient) {

		String[] messages = this.webClient.get()//
				.uri(this.messagesBaseUri)//
				.attributes(
						ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))//
				.retrieve()//
				.bodyToMono(String[].class)//
				.block();
		model.addAttribute("messages", messages);
		// 跳转到首页
		return "index";
	}

}
