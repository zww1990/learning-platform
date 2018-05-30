package com.example.dubbo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dubbo")
public class DubboProperties {
	private String appName;
	private String registryAddress;
	private boolean enable;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
