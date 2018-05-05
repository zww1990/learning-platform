package com.example.dubbo.config;

import java.util.TimeZone;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ZhangWeiWei
 * @date 2018年4月9日,下午2:19:11
 * @description Spring根配置类
 */
@Configuration
@EnableRabbit
@ComponentScan(basePackages = "com.example.dubbo")
@EnableDubbo(scanBasePackages = "com.example.dubbo.action")
@SuppressWarnings("rawtypes")
public class RootConfig {
	@Bean
	public ApplicationConfig applicationConfig(DubboProperties props) {
		return new ApplicationConfig(props.getAppName());
	}

	@Bean
	public RegistryConfig registryConfig(DubboProperties props) {
		return new RegistryConfig(props.getRegistryAddress());
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2018年4月8日,上午11:40:03
	 * @param props
	 * @return 定义mq连接工厂
	 */
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

	/**
	 * @author ZhangWeiWei
	 * @date 2018年4月2日,下午3:20:16
	 * @return 定义json转换器
	 */
	@Bean
	public ObjectMapper jsonObjectMapper() {
		return new ObjectMapper().setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
	}

	/**
	 * @author ZhangWeiWei
	 * @date 2018年4月10日,下午1:38:19
	 * @param connectionFactory
	 * @return 定义消息监听器容器
	 */
	@Bean
	public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
		SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
		containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		containerFactory.setConnectionFactory(connectionFactory);
		return containerFactory;
	}
}
