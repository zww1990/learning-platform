package com.example.seataclient.service;

import com.example.seataclient.domain.Account;
import com.example.seataclient.domain.Bill;
import com.example.seataclient.domain.Food;

/**
 * 账户服务接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:46:00
 */
public interface AccountService {

	/**
	 * 保存账单
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:47:16
	 * @param account 账户
	 * @param food    食品
	 * @param bill    账单
	 */
	void save(Account account, Food food, Bill bill);

}
