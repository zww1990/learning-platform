package com.example.hello.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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
	/** 使用map对象，便于根据Id来获取对应的SseEmitter */
	private Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

	@Override
	public SseEmitter connect(String id) {
		SseEmitter sseEmitter = new SseEmitter(-1L);
		sseEmitterMap.put(id, sseEmitter);
		log.info("创建新的sse连接，当前用户：{}", id);
		return sseEmitter;
	}

	@Override
	public void removeAll() {
		sseEmitterMap.forEach((id, sse) -> sse.complete());
		sseEmitterMap.clear();
		log.info("当前用户总数：{}", sseEmitterMap.size());
	}

	@Override
	public int count() {
		return sseEmitterMap.size();
	}

	@Override
	public void batchSendMessage(String message) {
		sseEmitterMap.forEach((id, sse) -> {
			try {
				sse.send(SseEmitter.event()//
						.id(UUID.randomUUID().toString().replace("-", ""))//
						.data(message, MediaType.TEXT_PLAIN));
			} catch (Exception e) {
				log.error("发送消息异常: {} -> {}", id, e.getLocalizedMessage());
				this.sseEmitterMap.remove(id);
			}
		});
	}

}
