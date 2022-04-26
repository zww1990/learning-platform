package com.example.springreactive.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:25:18
 */
@RestController
public class IndexController {
	@GetMapping("/")
	public Object index() {
		return Arrays.asList("你好，", "世界！");
	}
}
