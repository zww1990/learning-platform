package com.example.hello.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * MyEventListener2
 * 
 * @author zhang weiwei
 * @since 2022年8月14日,下午8:00:39
 */
@Service
@Slf4j
public class MyEventListener2 {
	@EventListener
	@Async
	public void eventListener(MyEvent event) {
		log.info("{}", event.getMsg());
	}
}
