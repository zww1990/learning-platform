package com.example.eurekaconsumer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 示例服务接口
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午9:53:26
 */
@FeignClient(name = "eureka-provider")
public interface HelloService {
	@GetMapping("/hello/say")
	List<String> say(@RequestParam String name);
}
