package com.example.seataclient;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 激活 @FeignClient
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(annotationClass = Mapper.class)
public class SeataClientCApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataClientCApplication.class, args);
	}

}
