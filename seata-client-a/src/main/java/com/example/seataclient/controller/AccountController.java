package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Bill;
import com.example.seataclient.domain.Food;
import com.example.seataclient.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountService accountService;

	@GetMapping("/submit")
	public ResponseEntity<String> submit() {
		// 初始化数据
		Account acc = new Account()//
				.setUserId(1)//
				.setUserName("张三")//
				.setBalance(20D);
		Food food = new Food()//
				.setFoodId(1)//
				.setFoodName("干脆面")//
				.setStock(5L);
		Bill bill = new Bill()//
				.setUserId(acc.getUserId())//
				.setAmount(acc.getBalance())//
				.setFoodId(food.getFoodId())//
				.setQuantity(food.getStock());
		this.accountService.save(acc, food, bill);
		return ResponseEntity.ok(bill.getBillNum());
	}
}
