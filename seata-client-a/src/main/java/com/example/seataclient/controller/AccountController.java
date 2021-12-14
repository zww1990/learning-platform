package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.mapper.AccountMapper;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountMapper accountMapper;
}
