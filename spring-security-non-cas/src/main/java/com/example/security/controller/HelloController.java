package com.example.security.controller;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@Resource
	private ServerProperties properties;

	@GetMapping("/hello")
	public Object hello() {
		return Arrays.asList("hello", this.properties.getPort());
	}

	@GetMapping("/admin/hello")
	public Object admin() {
		return Arrays.asList("admin", this.properties.getPort());
	}

	@GetMapping("/guest/hello")
	public Object guest() {
		return Arrays.asList("guest", this.properties.getPort());
	}
}
