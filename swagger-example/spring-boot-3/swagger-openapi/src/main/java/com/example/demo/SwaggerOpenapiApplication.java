package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SwaggerOpenapiApplication {
	public static Logger log = LoggerFactory.getLogger(SwaggerOpenapiApplication.class);

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(SwaggerOpenapiApplication.class);
		ConfigurableApplicationContext context = application.run(args);
		log.info("Spring Bean Definition Count = {}", context.getBeanDefinitionCount());
	}

}
