package com.example.seataclient.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.AccountMapper;

import io.seata.spring.annotation.GlobalTransactional;

public interface AccountService {
	int insert(Account account);

	int update(Account account);

	Account selectByUserId(Integer userId);

	void save(Account account, Food food);

	@Service
	public static class AccountServiceImpl implements AccountService {
		@Resource
		private AccountMapper accountMapper;
		@Resource
		private FoodService foodService;

		@Override
		public int insert(Account account) {
			return this.accountMapper.insert(account);
		}

		@Override
		public int update(Account account) {
			return this.accountMapper.update(account);
		}

		@Override
		public Account selectByUserId(Integer userId) {
			return this.accountMapper.selectByUserId(userId);
		}

		@Override
		@GlobalTransactional(rollbackFor = Exception.class)
		public void save(Account account, Food food) {
			this.accountMapper.update(account);
			this.foodService.update(food);
			// 故意制造异常，用于验证seata是否有效
			System.err.println(1 / 0);
		}

	}
}
