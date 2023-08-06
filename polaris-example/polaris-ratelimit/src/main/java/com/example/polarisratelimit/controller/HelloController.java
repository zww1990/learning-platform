package com.example.polarisratelimit.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@Autowired
	private ServerProperties server;

	@GetMapping("/get")
	public Object get() {
		log.info("服务{}：get()", this.server.getPort());
		return Map.of(this.server.getPort(), LocalDateTime.now());
	}
}
