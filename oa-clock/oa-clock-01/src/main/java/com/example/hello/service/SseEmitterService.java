package com.example.hello.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * SseEmitterService
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月3日,下午7:03:27
 */
public interface SseEmitterService {

	SseEmitter connect(String id);

	void removeAll();

	int count();

	void batchSendMessage(String message);

}
