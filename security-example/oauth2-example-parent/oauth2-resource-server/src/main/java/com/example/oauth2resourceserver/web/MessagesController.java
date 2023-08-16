package com.example.oauth2resourceserver.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息控制器
 * 
 * @author zhang weiwei
 * @since 2023年7月18日,下午6:05:43
 */
@RestController
public class MessagesController {

	@GetMapping("/messages")
	public String[] getMessages() {
		// 返回消息数据
		return new String[] { "消息 1", "消息 2", "消息 3" };
	}
}
