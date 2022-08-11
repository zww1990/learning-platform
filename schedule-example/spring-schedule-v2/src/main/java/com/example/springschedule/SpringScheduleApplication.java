package com.example.springschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * Spring Schedule Application
 * 
 * @author zhang weiwei
 * @since 2022年8月5日,下午8:17:32
 */
@SpringBootApplication(exclude = { ProjectInfoAutoConfiguration.class })
@Slf4j
public class SpringScheduleApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringScheduleApplication.class, args);
		log.info("应用程序上下文Bean定义计数={}", context.getBeanDefinitionCount());
	}

}
