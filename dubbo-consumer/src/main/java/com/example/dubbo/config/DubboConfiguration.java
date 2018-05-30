package com.example.dubbo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

/**
 * @author ZhangWeiWei
 * @date 2018年5月30日,下午2:49:53
 * @description Dubbo配置类
 */
@Configuration
@ConditionalOnProperty(name = "dubbo.enable", matchIfMissing = true)
@DubboComponentScan(basePackages = "com.example.dubbo.action")
public class DubboConfiguration {
	@Bean
	@ConditionalOnMissingBean
	public DubboProperties dubboProperties() {
		return new DubboProperties();
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "dubbo.app-name")
	public ApplicationConfig applicationConfig(DubboProperties props) {
		return new ApplicationConfig(props.getAppName());
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "dubbo.registry-address")
	public RegistryConfig registryConfig(DubboProperties props) {
		return new RegistryConfig(props.getRegistryAddress());
	}
}
