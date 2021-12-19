package com.example.seataclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.seataclient.domain.Bill;

/**
 * 账单微服务接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:57:02
 */
@FeignClient(name = "seata-client-c", path = "/bill")
public interface BillService {
	/**
	 * 创建账单
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:57:12
	 * @param bill 账单
	 * @return
	 */
	@PostMapping("/create")
	ResponseEntity<Bill> create(@RequestBody Bill bill);
}
