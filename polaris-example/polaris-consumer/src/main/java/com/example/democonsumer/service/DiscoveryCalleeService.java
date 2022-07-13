package com.example.democonsumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "polaris-provider")
public interface DiscoveryCalleeService {
	@GetMapping("/discovery/service/callee/sum")
	int sum(@RequestParam int value1, @RequestParam int value2);
}
