package com.example.democonsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 激活 @FeignClient
@EnableDiscoveryClient
@SpringBootApplication
public class DemoConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoConsumerApplication.class, args);
	}

}
