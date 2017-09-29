package com.example.dubbo.action;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubbo.service.DemoService;

@Component
public class BarAction {
	@Reference(version = "1.0.0")
	private DemoService service;

	public void exec(String text) {
		System.out.println(service.sayHello(text));
	}
}
