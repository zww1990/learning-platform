package com.example.springnative.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController
 * 
 * @author zhang weiwei
 * @since 2022年11月25日,下午1:29:54
 */
@RestController
public class IndexController {
	@GetMapping({ "", "/" })
	public Object index() {
		return Arrays.asList("hello", "world");
	}
}
