package com.example.oauth2client.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * 授权控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午7:15:34
 */
@Controller
public class AuthorizationController {
	private final WebClient webClient;
	private final String messagesBaseUri;

	public AuthorizationController(WebClient webClient, @Value("${messages.base-uri}") String messagesBaseUri) {
		this.webClient = webClient;
		this.messagesBaseUri = messagesBaseUri;
	}

	@GetMapping(value = "/authorize", params = "grant_type=authorization_code")
	public String authorizationCodeGrant(Model model,
			@RegisteredOAuth2AuthorizedClient("messaging-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {

		String[] messages = this.webClient//
				.get()//
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

	// '/authorized' is the registered 'redirect_uri' for authorization_code
	@GetMapping(value = "/authorized", params = OAuth2ParameterNames.ERROR)
	public String authorizationFailed(Model model, //
			@RequestParam(name = OAuth2ParameterNames.ERROR, required = false) String error, //
			@RequestParam(name = OAuth2ParameterNames.ERROR_DESCRIPTION, required = false) String description, //
			@RequestParam(name = OAuth2ParameterNames.ERROR_URI, required = false) String uri) {
		String errorCode = error;
		if (StringUtils.hasText(errorCode)) {
			model.addAttribute("error", new OAuth2Error(errorCode, description, uri));
		}
		// 跳转到首页
		return "index";
	}

	@GetMapping(value = "/authorize", params = "grant_type=client_credentials")
	public String clientCredentialsGrant(Model model) {

		String[] messages = this.webClient//
				.get()//
				.uri(this.messagesBaseUri)//
				.attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction
						.clientRegistrationId("messaging-client-client-credentials"))//
				.retrieve()//
				.bodyToMono(String[].class)//
				.block();
		model.addAttribute("messages", messages);
		// 跳转到首页
		return "index";
	}

	@GetMapping(value = "/authorize", params = "grant_type=device_code")
	public String deviceCodeGrant() {
		// 跳转到设备激活页
		return "device-activate";
	}

	@ExceptionHandler(WebClientResponseException.class)
	public String handleError(Model model, WebClientResponseException ex) {
		model.addAttribute("error", ex.getMessage());
		// 异常处理，跳转到首页
		return "index";
	}

}
