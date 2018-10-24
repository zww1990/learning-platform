package com.example.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

/**
 * @author ZhangWeiWei
 * @date 2018年10月24日,下午8:01:41
 * @description Dubbo配置类
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.example.dubbo.action")
public class DubboConfig {
	@Bean
	public ApplicationConfig applicationConfig(DubboProperties props) {
		return new ApplicationConfig(props.getAppName());
	}

	@Bean
	public RegistryConfig registryConfig(DubboProperties props) {
		return new RegistryConfig(props.getRegistryAddress());
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		ConsumerConfig config = new ConsumerConfig();
		config.setCheck(false);
		return config;
	}
}
