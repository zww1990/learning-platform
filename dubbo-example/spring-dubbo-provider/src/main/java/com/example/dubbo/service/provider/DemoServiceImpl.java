package com.example.dubbo.service.provider;

import org.apache.dubbo.config.annotation.DubboService;

import com.example.dubbo.service.DemoService;

import lombok.extern.slf4j.Slf4j;

@DubboService(version = "1.0.0")
@Slf4j
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		log.info("输入参数name={}", name);
		return "Hello " + name;
	}

}
