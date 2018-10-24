package com.example.dubbo.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangWeiWei
 * @date 2018年4月9日,下午2:19:11
 * @description Spring根配置类
 */
@Configuration
@ComponentScan(basePackages = "com.example.dubbo")
@Import({ DubboProperties.class, RabbitProperties.class, DubboConfig.class, RabbitConfig.class })
public class RootConfig {
	@Bean
	public ObjectMapper jsonObjectMapper() {
		return new ObjectMapper().setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}
}
