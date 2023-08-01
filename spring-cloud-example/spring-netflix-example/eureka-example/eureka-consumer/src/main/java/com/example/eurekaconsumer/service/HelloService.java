package com.example.eurekaconsumer.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * 示例服务接口
 * 
 * @author zhang weiwei
 * @since 2023年8月1日,下午9:53:26
 */
@HttpExchange(url = "http://localhost:9091/hello")
public interface HelloService {
	@GetExchange(url = "/say")
	List<String> say(@RequestParam String name);
}
