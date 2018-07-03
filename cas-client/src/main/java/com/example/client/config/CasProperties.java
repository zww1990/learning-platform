package com.example.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cas")
public class CasProperties {
	private String serverUrlPrefix;
	private String serverLoginUrl;
	private String clientUrl;
	private String mode;

	public String getServerUrlPrefix() {
		return serverUrlPrefix;
	}

	public void setServerUrlPrefix(String serverUrlPrefix) {
		this.serverUrlPrefix = serverUrlPrefix;
	}

	public String getServerLoginUrl() {
		return serverLoginUrl;
	}

	public void setServerLoginUrl(String serverLoginUrl) {
		this.serverLoginUrl = serverLoginUrl;
	}

	public String getClientUrl() {
		return clientUrl;
	}

	public void setClientUrl(String clientUrl) {
		this.clientUrl = clientUrl;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[serverUrlPrefix=").append(serverUrlPrefix).append(", serverLoginUrl=").append(serverLoginUrl)
				.append(", clientUrl=").append(clientUrl).append(", mode=").append(mode).append("]");
		return builder.toString();
	}
}
