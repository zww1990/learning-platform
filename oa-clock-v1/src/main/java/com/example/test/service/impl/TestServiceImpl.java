package com.example.test.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.example.test.service.TestService;

/**
 * service
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:15:16
 */
@Service
public class TestServiceImpl implements TestService {

	@Override
	public Object sayHello() {
		return Arrays.asList("hello", ", ", "hello");
	}

}
