package com.example.springreactive.controller;

import javax.annotation.Resource;

import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springreactive.model.ClientUser;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * UserController
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午4:14:03
 */
@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private DatabaseClient databaseClient;

	@GetMapping("/get")
	public Flux<ClientUser> getClientUser() {
		return this.databaseClient.select()//
				.from(ClientUser.class)//
				.fetch()//
				.all();
	}

	@PostMapping("/add")
	public Mono<Integer> addClientUser(@RequestBody ClientUser user) {
		return this.databaseClient.insert()//
				.into(ClientUser.class)//
				.using(user)//
				.fetch()//
				.rowsUpdated();
	}
}
