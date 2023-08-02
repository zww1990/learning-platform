package com.example.demogateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 网关服务启动类
 * 
 * @author weiwei
 * @version v1
 * @since 2022年2月17日,下午2:10:11
 */
@SpringBootApplication
public class GatewayServerApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
