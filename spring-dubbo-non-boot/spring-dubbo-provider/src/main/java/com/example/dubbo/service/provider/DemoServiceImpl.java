package com.example.dubbo.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.service.DemoService;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
	private static final Logger log = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Override
	public String sayHello(String name) {
		log.info("name={}", name);
		return "Hello " + name;
	}
}
