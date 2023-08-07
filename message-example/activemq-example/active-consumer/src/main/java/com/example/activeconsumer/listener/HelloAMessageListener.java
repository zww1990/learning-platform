package com.example.activeconsumer.listener;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.provider.api.domain.Hello;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息监听器A
 * 
 * @author zhang weiwei
 * @since 2023年8月4日,下午3:57:48
 */
@Component
@Slf4j
public class HelloAMessageListener implements MessageListener {
	static final String TOPIC_NAME = "hello";

	@JmsListener(destination = TOPIC_NAME, concurrency = "1")
	@Override
	public void onMessage(Message message) {
		try {
			Hello hello = (Hello) ((ActiveMQObjectMessage) message).getObject();
			log.info("onMessage()->{}: {}", TOPIC_NAME, hello);
		} catch (JMSException e) {
			log.error(e.getLocalizedMessage(), e);
		}
	}
}
