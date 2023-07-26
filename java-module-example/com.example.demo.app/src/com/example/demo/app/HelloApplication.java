package com.example.demo.app;

import java.util.ServiceLoader;

import com.example.demo.api.IHelloService;
//import com.example.demo.service.HelloService;

/**
 * 应用程序入口类
 * 
 * @author zhang weiwei
 * @since 2023年7月26日,下午8:44:55
 */
public class HelloApplication {
	public static void main(String[] args) {
//        IHelloService service = new HelloService();
		IHelloService service = ServiceLoader.load(IHelloService.class).findFirst().orElseThrow();
		System.err.println(service.getMessage("模块com.example.demo.app"));
	}
}
