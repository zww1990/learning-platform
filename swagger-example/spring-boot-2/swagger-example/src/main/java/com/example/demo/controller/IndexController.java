package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping
@ApiIgnore("忽略根访问路径")
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "你好，世界！";
	}
}
