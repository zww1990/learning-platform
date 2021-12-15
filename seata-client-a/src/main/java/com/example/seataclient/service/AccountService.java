package com.example.seataclient.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.AccountMapper;

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
		@Transactional(rollbackFor = Exception.class)
		public void save(Account account, Food food) {
			this.accountMapper.update(account);
			this.foodService.update(food);
		}

	}
}
