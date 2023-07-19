package com.example.springsecurityrestapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月19日,下午1:46:03
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	@GetMapping("/hello")
	public Object hello() {
		return List.of("hello world");
	}
}
