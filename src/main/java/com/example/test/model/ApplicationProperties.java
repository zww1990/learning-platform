package com.example.test.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@ToString
public class ApplicationProperties {
	private String userLoginUrl;
	private String staffClockUrl;
	private String initStaffClockUrl;
}
