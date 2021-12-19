package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.seataclient.domain.Bill;

/**
 * 账单持久化接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:10:04
 */
@Mapper
public interface BillMapper {
	/**
	 * 插入记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:10:15
	 * @param bill 账单
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "billId")
	@Insert({ //
			"insert into bill (bill_num, user_id, food_id, order_time, amount, quantity)", //
			"values (#{billNum}, #{userId}, #{foodId}, #{orderTime}, #{amount}, #{quantity})",//
	})
	int insert(Bill bill);
}
