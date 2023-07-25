package com.example.mybatisfluent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MybatisFluentApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MybatisFluentApplication.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Bean
	MapperFactory mapperFactory() {
		// 定义fluent mybatis的MapperFactory
		return new MapperFactory();
	}
}
