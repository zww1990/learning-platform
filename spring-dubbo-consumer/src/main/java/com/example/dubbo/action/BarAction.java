package com.example.dubbo.action;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import com.example.dubbo.service.DemoService;

@Component
public class BarAction {
	@DubboReference(version = "1.0.0")
	private DemoService service;

	public void exec(String text) {
		System.out.println(service.sayHello(text));
	}
}
