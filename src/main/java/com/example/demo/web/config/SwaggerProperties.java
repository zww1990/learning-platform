package com.example.demo.web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("swagger")
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
	private boolean enable;
	private String basePackage;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTermsOfServiceUrl() {
		return termsOfServiceUrl;
	}

	public void setTermsOfServiceUrl(String termsOfServiceUrl) {
		this.termsOfServiceUrl = termsOfServiceUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[title=").append(title).append(", description=").append(description).append(", version=")
				.append(version).append(", termsOfServiceUrl=").append(termsOfServiceUrl).append(", name=").append(name)
				.append(", url=").append(url).append(", email=").append(email).append(", license=").append(license)
				.append(", licenseUrl=").append(licenseUrl).append(", enable=").append(enable).append(", basePackage=")
				.append(basePackage).append("]");
		return builder.toString();
	}

}
