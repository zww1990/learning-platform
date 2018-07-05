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
	@Value("${cas.mode}")
	private String casMode;

	public String getCasMode() {
		return casMode;
	}

	public void setCasMode(String casMode) {
		this.casMode = casMode;
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

	public String getCasClientUrl() {
		return casClientUrl;
	}

	public void setCasClientUrl(String casClientUrl) {
		this.casClientUrl = casClientUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[casServerUrlPrefix=").append(casServerUrlPrefix).append(", casServerLoginUrl=")
				.append(casServerLoginUrl).append(", casClientUrl=").append(casClientUrl).append(", casMode=")
				.append(casMode).append("]");
		return builder.toString();
	}
}
