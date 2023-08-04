package com.example.rabbitconsumer.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.provider.api.domain.Hello;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息监听器A
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午3:57:48
 */
@Component
@Slf4j
public class HelloAMessageListener implements ChannelAwareMessageListener {
	static final String QUEUE_NAME = "qu.hello.a";
	@Autowired
	private ObjectMapper jsonMapper;

	@Override
	@RabbitListener(queues = QUEUE_NAME, concurrency = "1")
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			Hello hello = this.jsonMapper.readValue(message.getBody(), Hello.class);
			log.info("onMessage()->{}: {}", QUEUE_NAME, hello);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
		}
	}
}
