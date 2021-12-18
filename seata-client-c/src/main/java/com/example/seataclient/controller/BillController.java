package com.example.seataclient.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Bill;
import com.example.seataclient.mapper.BillMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bill")
@Slf4j
public class BillController {
	@Resource
	private BillMapper billMapper;

	@PostMapping("/create")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Bill> create(@RequestBody Bill bill) {
		log.info("客户端C-->>create-->>{}", bill);
		this.billMapper.insert(//
				bill.setBillNum(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")//
						.format(LocalDateTime.now()))//
						.setOrderTime(LocalDateTime.now()));
		// 故意制造异常，用于验证seata是否有效
//		System.err.println(1 / 0);
		return ResponseEntity.ok(bill);
	}
}
