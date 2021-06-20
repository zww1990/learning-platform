package com.example.dubbo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author ZhangWeiWei
 * @date 2018年4月9日,下午2:19:11
 * @description Spring根配置类
 */
@Configuration
@ComponentScan(basePackages = "com.example.dubbo")
@Import({ DubboProperties.class, DubboConfig.class, RabbitProperties.class, RabbitConfig.class, RedisProperties.class,
		RedisConfig.class })
public class RootConfig {
}
