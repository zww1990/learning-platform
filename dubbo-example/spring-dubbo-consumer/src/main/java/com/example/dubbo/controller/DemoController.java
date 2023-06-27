package com.example.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dubbo.service.DemoService;

@RestController
public class DemoController {
	@DubboReference(version = "1.0.0")
	private DemoService service;

	@GetMapping("/sayhello")
	public String sayHello(@RequestParam String text) {
		return service.sayHello(text);
	}
}
