package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.slf4j.Slf4j;

/**
 * HelloApplication
 * @author weiwei
 * @version v1
 * @since 2022年11月7日,上午10:16:47
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class HelloApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HelloApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
