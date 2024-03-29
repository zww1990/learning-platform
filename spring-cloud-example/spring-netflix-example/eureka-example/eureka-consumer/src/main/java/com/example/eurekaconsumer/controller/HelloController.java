package com.example.eurekaconsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.provider.api.domain.Hello;
import com.example.provider.api.service.HelloService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 示例控制器
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午10:10:26
 */
@RestController
@RequestMapping("/hello")
@Slf4j
@AllArgsConstructor
public class HelloController {
	private final HelloService helloService;

	@GetMapping("/get")
	public Hello get(@RequestParam String name) {
		log.info("消费者：get(): name = {}", name);
		return this.helloService.get(name);
	}
}
