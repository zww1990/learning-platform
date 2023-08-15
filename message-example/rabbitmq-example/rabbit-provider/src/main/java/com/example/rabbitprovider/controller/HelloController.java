package com.example.rabbitprovider.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
	private final RabbitTemplate rabbitTemplate;

	@PostMapping("/send")
	public Hello send(@RequestBody Hello hello) {
		log.info("send(): {}", hello);
		this.rabbitTemplate.convertAndSend("ex.hello", "rk.hello", //
				hello.setId(System.currentTimeMillis())//
						.setSendtime(LocalDateTime.now())//
						.setBirthday(LocalDate.now().minusYears(hello.getAge())));
		return hello;
	}
}
