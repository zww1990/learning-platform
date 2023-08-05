package com.example.rabbitprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

/**
 * Rabbit生产者启动类
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午1:27:24
 */
@SpringBootApplication
@Slf4j
public class RabbitProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RabbitProviderApplication.class, args);
		log.info("Get Bean Definition Count = {}", context.getBeanDefinitionCount());
//		java.util.stream.Stream.of(context.getBeanDefinitionNames()).forEach(System.err::println);
	}

	/**
	 * 可用于自定义RabbitTemplate的回调接口。
	 * 
	 * @author zhang weiwei
	 * @since 2023年8月5日,下午9:24:03
	 * @return
	 */
	@Bean
	RabbitTemplateCustomizer rabbitTemplateCustomizer() {
		return rabbitTemplate -> {
			//在发送消息时设置强制性标志；仅在提供了returnCallback的情况下应用。
			rabbitTemplate.setMandatory(true);
			// 设置一个回调来接收返回的消息。
			rabbitTemplate.setReturnsCallback(returned -> {
				Object message = rabbitTemplate.getMessageConverter().fromMessage(returned.getMessage());
				log.warn("返回的消息和元数据>> {}", returned);
				log.warn("将消息转换为Java对象>> {}", message);
			});
			// 设置一个发布者确认的回调。
			rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
				if (ack) {
					log.info("{}", correlationData);
				} else {
					log.error("{} - {}", correlationData, cause);
				}
			});
		};
	}
}
