package com.example.democonsumer.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.democonsumer.service.DiscoveryCalleeService;

@RestController
@RequestMapping("/discovery/service/caller")
public class DiscoveryCallerController {
	@Resource
	private RestTemplate restTemplate;
	@Resource
	private DiscoveryCalleeService discoveryCalleeService;

	@GetMapping("/feign")
	public int feign(@RequestParam int value1, @RequestParam int value2) {
		return discoveryCalleeService.sum(value1, value2);
	}

	@GetMapping("/rest")
	public String rest() {
		return restTemplate.getForObject("http://polaris-provider/discovery/service/callee/info", String.class);
	}
}
