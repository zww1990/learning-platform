package com.example.demoprovider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "teacher")
@Getter
@Setter
@ToString
public class Person {
	private String name;
	private int age;
}
