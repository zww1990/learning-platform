package com.example.democonsumer.demos.nacosdiscoveryconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nacos-provider", path = "/nacosprovider") // 指向服务提供者应用
public interface EchoService {

	@GetMapping("/echo/{message}")
	String echo(@PathVariable String message);
}
