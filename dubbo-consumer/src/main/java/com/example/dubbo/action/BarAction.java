package com.example.dubbo.action;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.example.dubbo.service.DemoService;

@Component
public class BarAction {
	private static final Logger log = LoggerFactory.getLogger(BarAction.class);
	@Reference(version = "1.0.0")
	private DemoService service;

	public void exec() {
		// 在服务消费方端设置隐式参数
		RpcContext.getContext().setAttachment("UUID", UUID.randomUUID().toString());
		log.info("sayHello={}", this.service.sayHello("5i5j"));
	}
}
