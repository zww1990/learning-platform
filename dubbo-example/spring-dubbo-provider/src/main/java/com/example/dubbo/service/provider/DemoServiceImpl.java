package com.example.dubbo.service.provider;

import org.apache.dubbo.config.annotation.DubboService;

import com.example.dubbo.service.DemoService;

@DubboService(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		System.err.println("输入参数name=" + name);
		return "Hello " + name;
	}

}
