package com.example.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dubbo.service.DemoService;

@Controller
public class DemoController {
	@DubboReference(version = "1.0.0")
	private DemoService service;

	@GetMapping("/sayhello")
	public String sayHello(@RequestParam String text) {
		return service.sayHello(text);
	}
}
