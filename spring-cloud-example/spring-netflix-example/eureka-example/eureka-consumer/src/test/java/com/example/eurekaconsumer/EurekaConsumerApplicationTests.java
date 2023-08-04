package com.example.eurekaconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.loadbalancer.FeignBlockingLoadBalancerClient;
import org.springframework.context.ApplicationContext;

import feign.Client;

@SpringBootTest
public class EurekaConsumerApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	public void testContextLoads() {
		FeignBlockingLoadBalancerClient client = (FeignBlockingLoadBalancerClient) this.context.getBean(Client.class);
		System.err.println(client.getDelegate().getClass());
		DiscoveryClient discoveryClient = this.context.getBean(DiscoveryClient.class);
		System.err.println(discoveryClient);
		LoadBalancerClient loadBalancerClient = this.context.getBean(LoadBalancerClient.class);
		System.err.println(loadBalancerClient);
	}

}
