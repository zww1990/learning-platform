package com.example.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

@Configuration
@ComponentScan(basePackages = "com.example.dubbo")
public class AppConfig {
	@Bean
	public static AnnotationBean annotationBean(Environment env) {
		AnnotationBean bean = new AnnotationBean();
		bean.setPackage(env.getProperty("dubbo.annotation.package"));
		return bean;
	}

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

	@Bean
	public ProtocolConfig protocolConfig(AppProperties props) {
		ProtocolConfig config = new ProtocolConfig();
		config.setName(props.getProtocolName());
		config.setPort(props.getProtocolPort());
		return config;
	}
}
