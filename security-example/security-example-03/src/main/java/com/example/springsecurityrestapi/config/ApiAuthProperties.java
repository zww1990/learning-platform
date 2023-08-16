package com.example.springsecurityrestapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * 接口请求头自定义属性
 * 
 * @author zhang weiwei
 * @since 2023年7月19日,下午1:40:15
 */
@Configuration
@ConfigurationProperties(prefix = "api.auth")
@Getter
@Setter
public class ApiAuthProperties {
	/** 请求头名称 */
	private String headerName;
	/** 请求头值 */
	private String headerValue;
}
