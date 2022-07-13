package com.example.dubbo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.dubbo.listener.Demo1ConsumerListener;
import com.example.dubbo.listener.Demo2ConsumerListener;
import com.example.dubbo.listener.Demo3ConsumerListener;
import com.example.dubbo.listener.Demo4ConsumerListener;

@Configuration
@ConditionalOnProperty(name = "rabbitmq.enable", matchIfMissing = true)
public class RabbitmqConfiguration {
	@Bean
	public Demo1ConsumerListener demo1ConsumerListener() {
		return new Demo1ConsumerListener();
	}

	@Bean
	public Demo2ConsumerListener demo2ConsumerListener() {
		return new Demo2ConsumerListener();
	}

	@Bean
	public Demo3ConsumerListener demo3ConsumerListener() {
		return new Demo3ConsumerListener();
	}

	@Bean
	public Demo4ConsumerListener demo4ConsumerListener() {
		return new Demo4ConsumerListener();
	}
}
