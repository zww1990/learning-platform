package com.example.dubbo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangWeiWei
 * @date 2018年4月9日,下午2:19:11
 * @description Rabbit配置类
 */
@Configuration
@ConditionalOnProperty(name = "rabbitmq.enable", matchIfMissing = true)
public class RabbitConfiguration {

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper jsonMapper) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(jsonMapper));
		// rabbitTemplate.setMandatory(true);
		return rabbitTemplate;
	}

	@Bean
	Queue queue1() {
		return QueueBuilder.durable("qu.demo1").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	Queue queue2() {
		return QueueBuilder.durable("qu.demo2").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	Queue queue3() {
		return QueueBuilder.durable("qu.demo3").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	Queue queue4() {
		return QueueBuilder.durable("qu.demo4").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	DirectExchange directExchange() {
		return ExchangeBuilder.directExchange("ex.demo").build();
	}

	@Bean
	Binding binding1(DirectExchange directExchange, Queue queue1) {
		return BindingBuilder.bind(queue1).to(directExchange).with("rk.demo1");
	}

	@Bean
	Binding binding2(DirectExchange directExchange, Queue queue2) {
		return BindingBuilder.bind(queue2).to(directExchange).with("rk.demo1");
	}

	@Bean
	Binding binding3(DirectExchange directExchange, Queue queue3) {
		return BindingBuilder.bind(queue3).to(directExchange).with("rk.demo2");
	}

	@Bean
	Binding binding4(DirectExchange directExchange, Queue queue4) {
		return BindingBuilder.bind(queue4).to(directExchange).with("rk.demo2");
	}
}
