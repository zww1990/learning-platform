//package com.example.dubbo.listener;
//
//import java.util.List;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.Channel;
//
//import jakarta.annotation.Resource;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author ZhangWeiWei
// * @date 2018年1月17日,下午6:42:24
// * @description
// */
//@SuppressWarnings("unchecked")
//@Slf4j
//public class Demo1ConsumerListener implements ChannelAwareMessageListener {
//	@Resource
//	private ObjectMapper jsonMapper;
//
//	@Override
//	@RabbitListener(queues = "qu.demo1")
//	public void onMessage(Message message, Channel channel) throws Exception {
//		try {
//			List<String> values = this.jsonMapper.readValue(message.getBody(), List.class);
//			log.info("MQ接收到消息1：{}", values);
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		} catch (Exception e) {
//			log.error(e.getLocalizedMessage());
//			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//		}
//	}
//}
