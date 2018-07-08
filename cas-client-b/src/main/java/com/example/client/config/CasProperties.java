package com.example.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("cas")
public class CasProperties {
	private String serverUrlPrefix;
	private String serverLoginUrl;
	private String clientUrl;
	private EnvMode envMode;

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

	public EnvMode getEnvMode() {
		return envMode;
	}

	public void setEnvMode(EnvMode envMode) {
		this.envMode = envMode;
	}

	public enum EnvMode {
		DEV, PROD
	}
}
