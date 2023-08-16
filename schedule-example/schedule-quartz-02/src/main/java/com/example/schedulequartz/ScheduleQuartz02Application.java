package com.example.schedulequartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring Schedule Application
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:17:32
 */
@SpringBootApplication
@Slf4j
public class ScheduleQuartz02Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ScheduleQuartz02Application.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

}
