package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.example.hello.config.ApplicationProperties;
import com.example.hello.service.BisearchServer;
import com.example.hello.service.StaffdbService;

import lombok.extern.slf4j.Slf4j;

/**
 * spring boot application
 * 
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:16:15
 */
@SpringBootApplication
@Slf4j
@EnableScheduling
public class HelloApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HelloApplication.class, args);
		log.info("当前容器中的bean总数={}", context.getBeanDefinitionCount());
//		java.util.Arrays.stream(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	@Bean
	StaffdbService staffdbService() {
		WebClient client = WebClient.builder().baseUrl(this.properties.getStaffDbUrl()).build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
		return factory.createClient(StaffdbService.class);
	}

	@Bean
	BisearchServer bisearchServer() {
		WebClient client = WebClient.builder().baseUrl(this.properties.getBiUrl()).build();
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
		return factory.createClient(BisearchServer.class);
	}

	@Autowired
	private ApplicationProperties properties;

	@Override
	public void run(String... args) throws Exception {
		log.info("{}", this.properties);
	}
}
