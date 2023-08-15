package com.example.dubbo.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dubbo.service.api.HelloService;
import com.example.dubbo.service.domain.Hello;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例控制器
 * 
 * @author zhang weiwei
 * @since 2023年8月2日,下午3:09:58
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
	@DubboReference(version = "1.0.0")
	private HelloService service;

	@GetMapping("/get")
	public Hello get(@RequestParam String name) {
		log.info("消费者：get(): name = {}", name);
		return service.get(name);
	}
}
