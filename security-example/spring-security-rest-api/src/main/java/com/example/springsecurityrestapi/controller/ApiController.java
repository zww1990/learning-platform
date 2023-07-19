package com.example.springsecurityrestapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月19日,下午1:46:03
 */
@RestController
public class ApiController {
	@GetMapping("/api/hello")
	public Object hello() {
		return List.of("hello world");
	}

	@GetMapping("/demo/hello")
	public Object hello2() {
		return List.of("你好世界！");
	}
}
