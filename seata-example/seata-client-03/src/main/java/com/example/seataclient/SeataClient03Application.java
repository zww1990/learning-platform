package com.example.seataclient;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 客户端03启动类
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:29:41
 */
@EnableFeignClients // 激活 @FeignClient
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(annotationClass = Mapper.class)
public class SeataClient03Application {

	/**
	 * 主方法
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:36:58
	 */
	public static void main(String[] args) {
		SpringApplication.run(SeataClient03Application.class, args);
	}

}
