package com.example.seataclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.seataclient.domain.Bill;

@FeignClient(name = "seata-client-c", path = "/bill")
public interface BillService {
	@PostMapping("/create")
	ResponseEntity<Bill> create(@RequestBody Bill bill);
}
