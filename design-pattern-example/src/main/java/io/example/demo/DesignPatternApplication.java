package io.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

import lombok.extern.slf4j.Slf4j;

/**
 * DesignPatternApplication
 * 
 * @author zhang weiwei
 * @since 2022年8月12日,下午8:45:37
 */
@SpringBootApplication
@EnableRetry
@Slf4j
public class DesignPatternApplication {

	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = SpringApplication.run(DesignPatternApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}
}
