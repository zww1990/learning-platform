package com.example.demoprovider.demos.nacosconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "user")
@Getter
@Setter
@ToString
public class User {

	private String name;

	private int age;

}
