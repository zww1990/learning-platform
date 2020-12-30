package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:swagger.properties")
public class SwaggerProperties {
	@Value("${swagger.title}")
	private String title;
	@Value("${swagger.description}")
	private String description;
	@Value("${swagger.version}")
	private String version;
	@Value("${swagger.terms-of-service-url}")
	private String termsOfServiceUrl;
	@Value("${swagger.name}")
	private String name;
	@Value("${swagger.url}")
	private String url;
	@Value("${swagger.email}")
	private String email;
	@Value("${swagger.license}")
	private String license;
	@Value("${swagger.license-url}")
	private String licenseUrl;
	@Value("${swagger.enable}")
	private boolean enable;
	@Value("${swagger.base-package}")
	private String basePackage;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getVersion() {
		return version;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getEmail() {
		return email;
	}

	public String getLicense() {
		return license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public boolean isEnable() {
		return enable;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setTermsOfServiceUrl(String termsOfServiceUrl) {
		this.termsOfServiceUrl = termsOfServiceUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

}
