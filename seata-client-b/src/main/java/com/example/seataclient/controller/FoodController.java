package com.example.seataclient.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.seataclient.mapper.FoodMapper;

@RestController
@RequestMapping("/food")
public class FoodController {
	@Resource
	private FoodMapper foodMapper;
}
