package com.example.hello.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

/**
 * MyEvent
 * 
 * @author zhang weiwei
 * @since 2022年8月14日,下午7:53:33
 */
@SuppressWarnings("serial")
@Getter
@Setter
public class MyEvent extends ApplicationEvent {
	private String msg;

	public MyEvent(Object source, String msg) {
		super(source);
		this.msg = msg;
	}
}
