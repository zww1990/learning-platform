package com.example.democonsumer.demos.nacosdiscoveryconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {

	@LoadBalanced
	@Autowired
	public RestTemplate restTemplate;

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@GetMapping("/call/echo/{message}")
	public String callEcho(@PathVariable String message) {
		// 访问应用 nacos-provider 的 REST "/echo/{message}"
		return restTemplate.getForObject("http://nacos-provider/echo/" + message, String.class);
	}
}
