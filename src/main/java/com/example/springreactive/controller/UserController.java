package com.example.springreactive.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private R2dbcEntityTemplate entityTemplate;

	@GetMapping("/get")
	public Flux<ClientUser> getClientUser() {
		return this.entityTemplate.select(ClientUser.class)//
				.matching(Query.empty().sort(Sort.by("userId")))//
				.all();
	}

	@PostMapping("/add")
	public Mono<ClientUser> addClientUser(@RequestBody ClientUser user) {
		return this.entityTemplate.insert(user);
	}

	@DeleteMapping("/del/{userId}")
	public Mono<Integer> delClientUser(@PathVariable String userId) {
		return this.entityTemplate.delete(ClientUser.class)//
				.matching(Query.query(Criteria.where("userId").is(userId)))//
				.all();
	}

	@PutMapping("/update")
	public Mono<ClientUser> updateClientUser(@RequestBody ClientUser user) {
		return this.entityTemplate.update(user);
	}
}
