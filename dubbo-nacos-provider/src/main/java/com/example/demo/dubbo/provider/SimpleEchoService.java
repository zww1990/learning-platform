package com.example.demo.dubbo.provider;

import org.apache.dubbo.config.annotation.DubboService;

import com.example.demo.dubbo.api.EchoService;

@DubboService
public class SimpleEchoService implements EchoService {

	@Override
	public String echo(String s) {
		return "[ECHO] " + s;
	}
}
