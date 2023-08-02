package com.example.provider.api.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.provider.api.domain.Hello;

/**
 * 示例服务接口
 * 
 * @author zhang weiwei
 * @since 2023年8月2日,上午11:37:44
 */
@FeignClient(name = "eureka-provider")
public interface HelloService {
	@GetMapping("/hello/get")
	Hello get(@RequestParam String name);
}
