package com.example.eurekaconsumer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eurekaconsumer.service.HelloService;

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
public class HelloController {
	@Autowired
	private HelloService helloService;

	@GetMapping("/say")
	public List<String> say(@RequestParam String name) {
		log.info("消费者：say(): name = {}", name);
		return this.helloService.say(name);
	}
}
