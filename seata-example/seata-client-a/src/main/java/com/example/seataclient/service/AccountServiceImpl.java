package com.example.seataclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Bill;
import com.example.seataclient.domain.Food;
import com.example.seataclient.mapper.AccountMapper;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;

/**
 * 账户服务实现类
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:48:31
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private FoodService foodService;
	@Autowired
	private BillService billService;

	@Override
	@GlobalTransactional(rollbackFor = Exception.class) // 使用该注解开启分布式事务
	public void save(Account account, Food food, Bill bill) {
		// 更新本地数据库
		log.info("账户>>>{}", account);
		this.accountMapper.update(account);
		// 调用微服务
		log.info("食品>>>{}", food);
		this.foodService.update(food);
		log.info("账单>>>{}", bill);
		this.billService.create(bill);
		// 故意制造异常，用于验证seata是否有效
		System.err.println(1 / 0);
	}

}
