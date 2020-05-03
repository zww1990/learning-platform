package com.example.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cas.properties")
public class CasProperties {
	@Value("${cas.server-url-prefix}")
	private String casServerUrlPrefix;
	@Value("${cas.server-login-url}")
	private String casServerLoginUrl;
	@Value("${cas.client-host-url}")
	private String casClientHostUrl;
	@Value("${cas-env-mode}")
	private String casEnvMode;

	public String getCasEnvMode() {
		return casEnvMode;
	}

	public void setCasEnvMode(String casEnvMode) {
		this.casEnvMode = casEnvMode;
	}

	public String getCasServerUrlPrefix() {
		return casServerUrlPrefix;
	}

	public void setCasServerUrlPrefix(String casServerUrlPrefix) {
		this.casServerUrlPrefix = casServerUrlPrefix;
	}

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasClientHostUrl() {
		return casClientHostUrl;
	}

	public void setCasClientHostUrl(String casClientHostUrl) {
		this.casClientHostUrl = casClientHostUrl;
	}

}
