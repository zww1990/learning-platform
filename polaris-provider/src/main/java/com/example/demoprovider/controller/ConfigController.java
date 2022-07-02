package com.example.demoprovider.controller;

import javax.annotation.Resource;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoprovider.config.Person;

@RestController
@RefreshScope
@RequestMapping("/config")
public class ConfigController {
	@Resource
	private Person person;

	@GetMapping("/person")
	public String person() {
		return person.toString();
	}
}
