package com.example.seataclient.controller;

import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.FoodMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 食品控制器
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:02:37
 */
@RestController
@RequestMapping("/food")
@Slf4j
@AllArgsConstructor
public class FoodController {
	private FoodMapper foodMapper;

	/**
	 * 更新食品
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:02:44
	 * @param food 食品
	 */
	@PutMapping("/update")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Integer> update(@RequestBody Food food) {
		log.info("客户端B-->>update-->>{}", food);
		int row = this.foodMapper.update(food);
		// 故意制造异常，用于验证seata是否有效
//		System.err.println(1 / 0);
		return ResponseEntity.ok(row);
	}
}
