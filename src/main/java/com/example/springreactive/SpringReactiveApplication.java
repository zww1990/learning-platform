package com.example.springreactive;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

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
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()//
				.GET("/", request -> ServerResponse.ok().bodyValue(Arrays.asList("你好，", "世界！")))//
				.build();
	}
}
