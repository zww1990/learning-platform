package com.example.dubbo.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author ZhangWeiWei
 * @date 2018年10月24日,下午7:57:32
 * @description Rabbit MQ配置类
 */
@Conditional(RabbitConfig.RabbitCondition.class)
@EnableRabbit
public class RabbitConfig {
	@Bean
	public ConnectionFactory connectionFactory(RabbitProperties props) {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setHost(props.getHost());
		connectionFactory.setPort(props.getPort());
		connectionFactory.setUsername(props.getUsername());
		connectionFactory.setPassword(props.getPassword());
		connectionFactory.setVirtualHost(props.getVirtualHost());
		return connectionFactory;
	}

	@Bean
	public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(
			ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
		containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		containerFactory.setConnectionFactory(connectionFactory);
		return containerFactory;
	}

	public static class RabbitCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			return context.getEnvironment().getProperty("rabbitmq.enable", boolean.class, false);
		}

	}
}
