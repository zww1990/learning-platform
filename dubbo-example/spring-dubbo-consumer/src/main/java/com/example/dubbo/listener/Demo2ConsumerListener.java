package com.example.dubbo.listener;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;

/**
 * @author ZhangWeiWei
 * @date 2018年1月22日,下午8:31:38
 * @description
 */
@SuppressWarnings("unchecked")
public class Demo2ConsumerListener implements ChannelAwareMessageListener {
	private static final Logger logger = LoggerFactory.getLogger(Demo2ConsumerListener.class);
	@Resource
	private ObjectMapper jsonMapper;

	@Override
	@RabbitListener(queues = "qu.demo2")
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			List<String> values = this.jsonMapper.readValue(message.getBody(), List.class);
			logger.info("MQ接收到消息2：{}", values);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		}
	}
}
