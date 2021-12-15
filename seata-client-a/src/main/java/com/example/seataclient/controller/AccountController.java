package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Food;
import com.example.seataclient.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountService accountService;

	@GetMapping("/submit")
	public ResponseEntity<?> submit() {
		Account acc = new Account(1, "张三", 11D);
		Food food = new Food(1, "干脆面", 1L);
		this.accountService.save(acc, food);
		return ResponseEntity.ok().build();
	}
}
