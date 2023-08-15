package com.example.activeprovider.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.provider.api.domain.Hello;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hello")
@Slf4j
@AllArgsConstructor
public class HelloController {
	private final JmsTemplate jmsTemplate;

	@PostMapping("/send")
	public Hello send(@RequestBody Hello hello) {
		log.info("send(): {}", hello);
		hello.setId(System.currentTimeMillis())//
				.setSendtime(LocalDateTime.now())//
				.setBirthday(LocalDate.now().minusYears(hello.getAge()));
		this.jmsTemplate.convertAndSend(new ActiveMQTopic("hello"), hello);
		return hello;
	}
}
