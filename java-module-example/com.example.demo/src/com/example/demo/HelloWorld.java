package com.example.demo;

import com.example.demo.api.IHelloService;
import com.example.demo.service.HelloService;

/**
 * 接口实现入口类
 * 
 * @author zhang weiwei
 * @since 2023年7月26日,下午8:42:33
 */
public class HelloWorld {
	public static void main(String[] args) {
		IHelloService service = new HelloService();
		System.err.println(service.getMessage("模块com.example.demo"));
	}
}
