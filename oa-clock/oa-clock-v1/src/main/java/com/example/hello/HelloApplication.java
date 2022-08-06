package com.example.hello;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import com.example.hello.model.ApplicationProperties;

import lombok.extern.slf4j.Slf4j;

/**
 * spring boot application
 * 
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:16:15
 */
@EnableRetry
@SpringBootApplication
@Slf4j
public class HelloApplication implements CommandLineRunner {

	/**
	 * @param args
	 * @author zhangweiwei
	 * @date 2021年5月25日,下午4:16:28
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HelloApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Resource
	private ApplicationProperties properties;

	@Override
	public void run(String... args) throws Exception {
		log.info("{}", this.properties);
	}
}
