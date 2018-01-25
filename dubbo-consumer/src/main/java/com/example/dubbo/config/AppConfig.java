package com.example.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

@Configuration
@ComponentScan(basePackages = "com.example.dubbo")
@EnableDubbo(scanBasePackages = "com.example.dubbo.action")
public class AppConfig {
	@Bean
	public ApplicationConfig applicationConfig(AppProperties props) {
		ApplicationConfig config = new ApplicationConfig();
		config.setName(props.getAppName());
		return config;
	}

	@Bean
	public RegistryConfig registryConfig(AppProperties props) {
		RegistryConfig config = new RegistryConfig();
		config.setAddress(props.getRegistryAddress());
		return config;
	}
}
