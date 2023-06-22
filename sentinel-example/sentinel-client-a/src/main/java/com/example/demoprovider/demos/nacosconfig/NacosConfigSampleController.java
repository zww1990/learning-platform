package com.example.demoprovider.demos.nacosconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;

@RestController
@RefreshScope
public class NacosConfigSampleController {

	@Autowired
	private User user;

	@GetMapping("/user")
	@SentinelResource(value = "user", blockHandler = "handleException")
	public String user() {
		return "[HTTP] " + user;
	}

	public String handleException(BlockException be) {
		return "我靠，我被限流了！";
	}
}
