package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SwaggerProperties
 * 
 * @author zww19
 * @since 2022年1月29日,下午8:57:46
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "springdoc.swagger")
public class SwaggerProperties {
	private String title;
	private String description;
	private String version;
	private String termsOfServiceUrl;
	private String name;
	private String url;
	private String email;
	private String license;
	private String licenseUrl;

}
