package com.example.dubbo.config;

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
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangWeiWei
 * @date 2018年10月24日,下午6:33:34
 * @description Rabbit MQ配置类
 */
@Conditional(RabbitConfig.RabbitCondition.class)
public class RabbitConfig {

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
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper jsonObjectMapper) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter(jsonObjectMapper));
		// rabbitTemplate.setMandatory(true);
		return rabbitTemplate;
	}

	@Bean
	public RabbitAdmin amqpAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange("ex.demo");
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
	public Binding binding1() {
		return BindingBuilder.bind(this.queue1()).to(this.directExchange()).with("rk.demo1");
	}

	@Bean
	public Binding binding2() {
		return BindingBuilder.bind(this.queue2()).to(this.directExchange()).with("rk.demo1");
	}

	@Bean
	public Binding binding3() {
		return BindingBuilder.bind(this.queue3()).to(this.directExchange()).with("rk.demo2");
	}

	@Bean
	public Binding binding4() {
		return BindingBuilder.bind(this.queue4()).to(this.directExchange()).with("rk.demo2");
	}

	public static class RabbitCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("rabbitmq.enable", boolean.class, false);
		}

	}
}
