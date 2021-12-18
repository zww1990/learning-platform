package com.example.seataclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.seataclient.domain.Food;

@FeignClient(name = "seata-client-b", path = "/food")
public interface FoodService {
	@PutMapping("/update")
	ResponseEntity<Integer> update(@RequestBody Food food);
}
