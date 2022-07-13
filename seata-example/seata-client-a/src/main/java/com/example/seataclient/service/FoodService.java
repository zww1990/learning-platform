package com.example.seataclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.seataclient.domain.Food;

/**
 * 食品微服务接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:55:47
 */
@FeignClient(name = "seata-client-b", path = "/food")
public interface FoodService {
	/**
	 * 更新食品
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:56:06
	 * @param food 食品
	 * @return
	 */
	@PutMapping("/update")
	ResponseEntity<Integer> update(@RequestBody Food food);
}
