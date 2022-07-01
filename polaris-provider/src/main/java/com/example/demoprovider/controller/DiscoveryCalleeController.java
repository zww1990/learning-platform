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
		log.info("Discovery Service Callee [{}] is called.", this.properties.getPort());
		return String.format("Discovery Service Callee [%s] is called.", this.properties.getPort());
	}

	@GetMapping("/sum")
	public int sum(@RequestParam int value1, @RequestParam int value2) {
		log.info("Discovery Service Callee is called and sum is {}.", value1 + value2);
		return value1 + value2;
	}
}
