package com.example.rabbitprovider.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RabbitMQ配置类
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午3:18:08
 */
@Configuration
public class RabbitConfig {
	@Bean
	MessageConverter messageConverter(ObjectMapper jsonObjectMapper) {
		return new Jackson2JsonMessageConverter(jsonObjectMapper);
	}

	@Configuration
	@ConditionalOnProperty(prefix = "spring.rabbitmq", name = "dynamic", matchIfMissing = true)
	static class RabbitAutoDeclaringConfig implements CommandLineRunner {

		@Bean
		Queue queueA() {
			return QueueBuilder.durable("qu.hello.a").ttl(3600000).build();
		}

		@Bean
		Queue queueB() {
			return QueueBuilder.durable("qu.hello.b").ttl(3600000).build();
		}

		@Bean
		DirectExchange directExchange() {
			return ExchangeBuilder.directExchange("ex.hello").build();
		}

		@Bean
		Binding bindingA(DirectExchange directExchange, Queue queueA) {
			return BindingBuilder.bind(queueA).to(directExchange).with("rk.hello");
		}

		@Bean
		Binding bindingB(DirectExchange directExchange, Queue queueB) {
			return BindingBuilder.bind(queueB).to(directExchange).with("rk.hello");
		}

		@Override
		public void run(String... args) throws Exception {
			((RabbitAdmin) this.amqpAdmin).initialize();
		}

		@Autowired
		AmqpAdmin amqpAdmin;
	}
}
