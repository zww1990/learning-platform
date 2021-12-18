package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.FoodMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/food")
@Slf4j
public class FoodController {

	@Resource
	private FoodMapper foodMapper;

	@PutMapping("/update")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> update(@RequestBody Food food) {
		log.info("客户端B-->>update-->>{}", food);
		int row = this.foodMapper.update(food);
		// 故意制造异常，用于验证seata是否有效
//		System.err.println(1 / 0);
		return ResponseEntity.ok(row);
	}
}
