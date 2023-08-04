package com.example.polarisconsumer.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.context.ApplicationContext;

import feign.Client;

@SpringBootTest
public class ConfigControllerTests {
	@Autowired
	private ConfigController controller;
	@Autowired
	private ApplicationContext context;

	@Test
	public void testStudent() {
		try {
			System.err.println(this.controller.get());
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
