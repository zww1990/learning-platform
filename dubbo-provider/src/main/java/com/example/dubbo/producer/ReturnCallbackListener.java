package com.example.dubbo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * 返回回调监听器
 * @author ZhangWeiWei
 * @date 2018年1月17日,下午2:20:36
 * @description
 */
public class ReturnCallbackListener implements ReturnCallback {
	private static final Logger logger = LoggerFactory.getLogger(ReturnCallbackListener.class);

	/**
	 * 返回消息回调.
	 * @param message 返回的消息.
	 * @param replyCode 回复码.
	 * @param replyText 回复文本.
	 * @param exchange 消息交换机.
	 * @param routingKey 路由关键字.
	 */
	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		try {
			logger.info("MQ消息路由到queue: message={},exchange={},routingKey={},replyCode={},replyText={}",
					new String(message.getBody()), exchange, routingKey, replyCode, replyText);
		} catch (Exception ex) {
			logger.error("MQ消息路由到queue异常: message={},exchange={},routingKey={},replyCode={},replyText={},error={}",
					new String(message.getBody()), exchange, routingKey, replyCode, replyText, ex.getMessage(), ex);
		}
	}
}
