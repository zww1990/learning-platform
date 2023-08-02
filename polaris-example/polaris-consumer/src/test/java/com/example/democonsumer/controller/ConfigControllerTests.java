package com.example.democonsumer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.context.ApplicationContext;

import feign.Client;
import jakarta.annotation.Resource;

@SpringBootTest
public class ConfigControllerTests {
	@Resource
	private ConfigController controller;
	@Resource
	private ApplicationContext context;

	@Test
	public void testStudent() {
		try {
			System.err.println(this.controller.student());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testContextLoads() {
		FeignBlockingLoadBalancerClient client = (FeignBlockingLoadBalancerClient) this.context.getBean(Client.class);
		System.err.println(client.getDelegate().getClass());
	}

}
