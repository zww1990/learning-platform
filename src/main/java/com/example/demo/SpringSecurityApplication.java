package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SpringSecurityApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
		String[] names = context.getBeanDefinitionNames();
		for (int i = 0, len = names.length; i < len; i++) {
			System.err.println((i + 1) + "\t" + names[i]);
		}
	}
}
