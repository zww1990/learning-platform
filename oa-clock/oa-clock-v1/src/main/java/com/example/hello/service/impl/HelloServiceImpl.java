package com.example.hello.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.hello.service.HelloService;

/**
 * service
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:15:16
 */
@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public Object sayHello() {
		return Arrays.asList("hello", ", ", "hello");
	}

}
