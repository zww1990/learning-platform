package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
	private String basePackage;
	private String groupName;

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

	public String getBasePackage() {
		return basePackage;
	}

	public String getGroupName() {
		return groupName;
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

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
