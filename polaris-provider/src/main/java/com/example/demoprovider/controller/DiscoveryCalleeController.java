package com.example.demoprovider.controller;

import javax.annotation.Resource;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/discovery/service/callee")
@Slf4j
public class DiscoveryCalleeController {
	@Resource
	private ServerProperties properties;

	@GetMapping("/info")
	public String info() {
		log.info("服务提供者[{}]的info方法被调用了。", this.properties.getPort());
		return String.format("服务提供者[{}]的info方法被调用了。", this.properties.getPort());
	}

	@GetMapping("/sum")
	public int sum(@RequestParam int value1, @RequestParam int value2) {
		log.info("服务提供者的sum方法调用结果是[{}]。", value1 + value2);
		return value1 + value2;
	}
}
