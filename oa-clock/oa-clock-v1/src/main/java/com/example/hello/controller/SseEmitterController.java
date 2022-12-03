package com.example.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.example.hello.model.ResponseBody;
import com.example.hello.service.SseEmitterService;

/**
 * SseEmitterController
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月3日,下午7:05:31
 */
@RestController
@RequestMapping("/sse")
public class SseEmitterController {
	@Autowired
	private SseEmitterService emitterService;

	@GetMapping("/connect/{id}")
	public SseEmitter connect(@PathVariable String id) {
		return this.emitterService.connect(id);
	}

	@GetMapping("/close/{id}")
	public ResponseBody<?> close(@PathVariable String id) {
		this.emitterService.remove(id);
		return ResponseBody.success().setMessage("连接关闭！");
	}

	@PostMapping("/push")
	public ResponseBody<?> push(@RequestParam String message) {
		int count = this.emitterService.count();
		if (count < 1) {
			return ResponseBody.failure().setMessage("无人在线！");
		}
		this.emitterService.batchSendMessage(message);
		return ResponseBody.success().setMessage("发送成功！");
	}
}
