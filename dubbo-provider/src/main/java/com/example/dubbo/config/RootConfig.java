package com.example.dubbo.config;

import java.util.TimeZone;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.example.dubbo.producer.ConfirmCallbackListener;
import com.example.dubbo.producer.ReturnCallbackListener;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangWeiWei
 * @date 2018年4月9日,下午2:19:11
 * @description Spring根配置类
 */
@Configuration
@ComponentScan(basePackages = "com.example.dubbo")
@EnableDubbo(scanBasePackages = "com.example.dubbo.service.provider")
public class RootConfig {
	@Bean
	public ApplicationConfig applicationConfig(DubboProperties props) {
		return new ApplicationConfig(props.getAppName());
	}

	@Bean
	public RegistryConfig registryConfig(DubboProperties props) {
		return new RegistryConfig(props.getRegistryAddress());
	}

	@Bean
	public ProtocolConfig protocolConfig(DubboProperties props) {
		return new ProtocolConfig(props.getProtocolName(), props.getProtocolPort());
	}

	@Bean
	public ConnectionFactory connectionFactory(RabbitProperties props) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(props.getHost());
		connectionFactory.setPort(props.getPort());
		connectionFactory.setUsername(props.getUsername());
		connectionFactory.setPassword(props.getPassword());
		connectionFactory.setVirtualHost(props.getVirtualHost());
		connectionFactory.setPublisherConfirms(true);
		return connectionFactory;
	}

	@Bean
	public ObjectMapper jsonObjectMapper() {
		return new ObjectMapper().setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper jsonMapper) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(jsonMapper));
		rabbitTemplate.setConfirmCallback(new ConfirmCallbackListener());
		rabbitTemplate.setReturnCallback(new ReturnCallbackListener());
		// rabbitTemplate.setMandatory(true);
		return rabbitTemplate;
	}

	@Bean
	public RabbitAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public Queue queue1() {
		return QueueBuilder.durable("qu.demo1").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	public Queue queue2() {
		return QueueBuilder.durable("qu.demo2").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	public Queue queue3() {
		return QueueBuilder.durable("qu.demo3").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	public Queue queue4() {
		return QueueBuilder.durable("qu.demo4").withArgument("x-message-ttl", 5000).build();
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("ex.demo");
	}

	@Bean
	public Binding binding1(DirectExchange directExchange, Queue queue1) {
		return BindingBuilder.bind(queue1).to(directExchange).with("rk.demo1");
	}

	@Bean
	public Binding binding2(DirectExchange directExchange, Queue queue2) {
		return BindingBuilder.bind(queue2).to(directExchange).with("rk.demo1");
	}

	@Bean
	public Binding binding3(DirectExchange directExchange, Queue queue3) {
		return BindingBuilder.bind(queue3).to(directExchange).with("rk.demo2");
	}

	@Bean
	public Binding binding4(DirectExchange directExchange, Queue queue4) {
		return BindingBuilder.bind(queue4).to(directExchange).with("rk.demo2");
	}
}
