package com.example.democonsumer.demos.nacosconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class NacosConfigSampleController {

	@Autowired
	private User user;

	@GetMapping("/user")
	public String user() {
		return "[HTTP] " + user;
	}

}
