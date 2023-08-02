package com.example.adminserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * Admin服务启动类
 * 
 * @author weiwei
 * @version v1
 * @since 2022年1月27日,下午3:28:06
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AdminServerApplication.class, args);
	}
}
