package com.example.demo.controller;

import java.util.Arrays;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping
@ApiIgnore("忽略根访问路径")
public class IndexController {
	@GetMapping("/")
	public Object index() {
		return Arrays.asList("你好，", "世界！");
	}
}
