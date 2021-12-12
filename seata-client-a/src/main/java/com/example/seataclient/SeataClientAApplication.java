package com.example.seataclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 激活 @FeignClient
@EnableDiscoveryClient
@SpringBootApplication
public class SeataClientAApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataClientAApplication.class, args);
	}

}
