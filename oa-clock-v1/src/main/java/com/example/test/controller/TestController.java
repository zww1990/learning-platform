package com.example.test.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.service.TestService;

/**
 * controller
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:14:04
 */
@RestController
@RequestMapping("/test")
public class TestController {
	@Resource
	private TestService service;

	/**
	 * @return
	 * @author zhangweiwei
	 * @date 2021年5月25日,下午4:14:46
	 */
	@GetMapping
	public Object index() {
		return this.service.sayHello();
	}
}
