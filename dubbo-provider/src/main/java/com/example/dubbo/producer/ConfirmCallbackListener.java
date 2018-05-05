package com.example.dubbo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * 确认回调监听器
 * @author ZhangWeiWei
 * @date 2018年1月17日,下午2:38:22
 * @description
 */
public class ConfirmCallbackListener implements ConfirmCallback {
	private static final Logger logger = LoggerFactory.getLogger(ConfirmCallbackListener.class);

	/**
	 * 确认回调.
	 * @param correlationData 回调的相关数据.
	 * @param ack true为已确认, false为未确认
	 * @param cause 一个可选的原因, 只有未确认时才不为空, 否则为空.
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		try {
			logger.info("MQ消息发送到exchange: correlationData={},ack={},cause={}", correlationData, ack, cause);
		} catch (Exception ex) {
			logger.error("MQ消息发送到exchange异常: correlationData={},ack={},cause={},error={}", correlationData, ack, cause,
					ex.getMessage(), ex);
		}
	}
}
