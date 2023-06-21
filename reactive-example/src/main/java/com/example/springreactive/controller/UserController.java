package com.example.springreactive.controller;

import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.springreactive.model.ClientUser;

import jakarta.annotation.Resource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * UserController
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午4:14:03
 */
@Service
public class UserController {
	@Resource
	private R2dbcEntityTemplate entityTemplate;

	public Flux<ClientUser> getClientUser() {
		return this.entityTemplate.select(ClientUser.class)//
				.matching(Query.empty().sort(Sort.by("userId")))//
				.all();
	}

	public Mono<ClientUser> addClientUser(ClientUser user) {
		return this.entityTemplate.insert(user);
	}

	public Mono<Long> delClientUser(String userId) {
		return this.entityTemplate.delete(ClientUser.class)//
				.matching(Query.query(Criteria.where("userId").is(userId)))//
				.all();
	}

	public Mono<ClientUser> updateClientUser(ClientUser user) {
		return this.entityTemplate.update(user);
	}
}
