package com.example.dubbo.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.service.DemoService;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		System.out.println("输入参数name=" + name);
		return "Hello " + name;
	}

}
