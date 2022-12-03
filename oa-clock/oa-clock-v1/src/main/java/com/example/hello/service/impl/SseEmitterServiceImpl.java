package com.example.hello.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.hello.service.SseEmitterService;

import lombok.extern.slf4j.Slf4j;

/**
 * SseEmitterServiceImpl
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月3日,下午7:58:21
 */
@Service
@Slf4j
public class SseEmitterServiceImpl implements SseEmitterService {
	/** 当前连接数 */
	private static AtomicInteger count = new AtomicInteger(0);
	/** 使用map对象，便于根据Id来获取对应的SseEmitter */
	private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

	@Override
	public SseEmitter connect(String id) {
		SseEmitter sseEmitter = new SseEmitter(0L);
		// 注册回调
		sseEmitter.onCompletion(() -> {
			log.info("结束连接：{}", id);
		});
		sseEmitter.onError(t -> {
			log.error("未知错误>>>{}", t.getLocalizedMessage());
		});
		sseEmitter.onTimeout(() -> {
			log.info("连接超时：{}", id);
		});
		sseEmitterMap.put(id, sseEmitter);
		// 数量+1
		count.getAndIncrement();
		log.info("创建新的sse连接，当前用户：{}", id);
		return sseEmitter;
	}

	@Override
	public void remove(String id) {
		sseEmitterMap.remove(id);
		// 数量-1
		count.getAndDecrement();
		log.info("移除用户：{}", id);
	}

	@Override
	public int count() {
		return count.intValue();
	}

	@Override
	public void batchSendMessage(String message) {
		sseEmitterMap.forEach((id, se) -> {
			try {
				se.send(SseEmitter.event().id(UUID.randomUUID().toString()).data(message, MediaType.TEXT_PLAIN));
			} catch (Exception e) {
				log.error("发送消息异常>>>{}", e.getLocalizedMessage());
			}
		});
	}

}
