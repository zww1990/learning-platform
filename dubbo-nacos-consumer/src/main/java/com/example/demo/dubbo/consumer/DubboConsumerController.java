package com.example.demo.dubbo.consumer;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dubbo.api.EchoService;

@RestController
public class DubboConsumerController {

	@DubboReference
	private EchoService echoService;

	@GetMapping("/echo")
	public String echo(@RequestParam String message) {
		return echoService.echo(message);
	}

}
