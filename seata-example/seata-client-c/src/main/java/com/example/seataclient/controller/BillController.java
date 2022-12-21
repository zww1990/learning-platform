package com.example.seataclient.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Bill;
import com.example.seataclient.mapper.BillMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 账单控制器
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:09:20
 */
@RestController
@RequestMapping("/bill")
@Slf4j
public class BillController {
	@Autowired
	private BillMapper billMapper;

	/**
	 * 创建账单
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:09:32
	 * @param bill 账单
	 * @return
	 */
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
