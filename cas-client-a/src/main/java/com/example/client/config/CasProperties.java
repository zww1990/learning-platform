package com.example.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * CAS客户端属性类
 * 
 * @author home
 */
@PropertySource("classpath:cas.properties")
public class CasProperties {
	@Value("${cas.server-url-prefix}")
	private String casServerUrlPrefix;
	@Value("${cas.server-login-url}")
	private String casServerLoginUrl;
	@Value("${cas.client-host-url}")
	private String casClientHostUrl;
	@Value("${cas-env-mode:PROD}")
	private CasEnvMode casEnvMode;
	@Value("${cas-ignore-patterns:}")
	private String casIgnorePatterns;

	public String getCasIgnorePatterns() {
		return casIgnorePatterns;
	}

	public void setCasIgnorePatterns(String casIgnorePatterns) {
		this.casIgnorePatterns = casIgnorePatterns;
	}

	public CasEnvMode getCasEnvMode() {
		return casEnvMode;
	}

	public void setCasEnvMode(CasEnvMode casEnvMode) {
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

	/**
	 * CAS客户端枚举类
	 * 
	 * @author home
	 */
	public enum CasEnvMode {
		/** 生产模式 */
		PROD,
		/** 开发模式 */
		DEV
	}
}
