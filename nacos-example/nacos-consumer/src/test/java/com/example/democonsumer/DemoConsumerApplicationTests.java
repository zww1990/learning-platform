package com.example.democonsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.context.ApplicationContext;

import feign.Client;

@SpringBootTest
public class DemoConsumerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		FeignBlockingLoadBalancerClient client = (FeignBlockingLoadBalancerClient) this.context.getBean(Client.class);
		System.err.println(client.getDelegate().getClass());
	}

}
