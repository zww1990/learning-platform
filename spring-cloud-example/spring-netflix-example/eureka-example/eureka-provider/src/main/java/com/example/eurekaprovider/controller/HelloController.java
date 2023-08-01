package com.example.eurekaprovider.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 示例控制器
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午9:04:50
 */
@RestController
@RequestMapping("/hello")
@Slf4j
public class HelloController {
	@GetMapping("/say")
	public List<String> say(@RequestParam String name) {
		log.info("say(): name = {}", name);
		return List.of("你好，[" + name + "]! ");
	}
}
