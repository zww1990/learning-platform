package com.example.hello.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * MyEventListener1
 * 
 * @author zhang weiwei
 * @since 2022年8月14日,下午7:56:33
 */
@Service
@Slf4j
public class MyEventListener1 {
	@EventListener
	public void eventListener(MyEvent event) {
		log.info("{}", event.getMsg());
	}
}
