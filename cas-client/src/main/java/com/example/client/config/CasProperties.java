package com.example.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cas.properties")
public class CasProperties {
	@Value("${cas.server.url}")
	private String casServerUrlPrefix;
	@Value("${cas.server.login.url}")
	private String casServerLoginUrl;
	@Value("${cas.client.url}")
	private String casClientUrl;

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

	public String getCasClientUrl() {
		return casClientUrl;
	}

	public void setCasClientUrl(String casClientUrl) {
		this.casClientUrl = casClientUrl;
	}
}
