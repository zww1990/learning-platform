package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.FoodMapper;

@RestController
@RequestMapping("/food")
public class FoodController {

	private static final Logger log = LoggerFactory.getLogger(FoodController.class);

	@Resource
	private FoodMapper foodMapper;

	@PutMapping("/update")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> update(@RequestBody Food food) {
		log.info("客户端B-->>update-->>{}", food);
		int row = this.foodMapper.update(food);
		System.err.println(1/0);
		return ResponseEntity.ok(row);
	}
}
