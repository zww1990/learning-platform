package com.example.security.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public Object hello() {
		return Arrays.asList("hello");
	}

	@GetMapping("/admin/hello")
	public Object admin() {
		return Arrays.asList("admin");
	}

	@GetMapping("/guest/hello")
	public Object guest() {
		return Arrays.asList("guest");
	}
}
