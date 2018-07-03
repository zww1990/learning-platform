package com.example.dubbo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * @author ZhangWeiWei
 * @date 2018年5月30日,下午2:49:53
 * @description Dubbo配置类
 */
@Configuration
@ConditionalOnProperty(name = "dubbo.enable", matchIfMissing = true)
@DubboComponentScan(basePackages = "com.example.dubbo.service")
@EnableConfigurationProperties(DubboProperties.class)
public class DubboConfiguration {

	@Bean
	public ApplicationConfig applicationConfig(DubboProperties props) {
		return new ApplicationConfig(props.getAppName());
	}

	@Bean
	public RegistryConfig registryConfig(DubboProperties props) {
		return new RegistryConfig(props.getRegistryAddress());
	}

	@Bean
	public ProtocolConfig protocolConfig(DubboProperties props) {
		return new ProtocolConfig(props.getProtocolName(), props.getProtocolPort());
	}
}
