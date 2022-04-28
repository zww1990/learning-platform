package com.example.springreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * SpringReactiveApplication
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:53:23
 */
@SpringBootApplication
@Slf4j
public class SpringReactiveApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringReactiveApplication.class, args);
		log.info("工厂中定义的 bean 数量 = {}", context.getBeanDefinitionCount());
	}

}
