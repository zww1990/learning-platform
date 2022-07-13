package com.example.democonsumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Configuration
@ConfigurationProperties(prefix = "student")
@Getter
@Setter
@ToString
public class Student {
	private String name;
	private int age;
}
