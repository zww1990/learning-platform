package com.example.demo.service;

import com.example.demo.api.IHelloService;

/**
 * 接口实现类
 * 
 * @author zhang weiwei
 * @since 2023年7月26日,下午8:40:48
 */
public class HelloService implements IHelloService {
	@Override
	public String getMessage(String text) {
		return text + ": Hello Java 9 Module System!";
	}
}
