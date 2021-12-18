package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import com.example.seataclient.domain.Bill;

@Mapper
public interface BillMapper {
	@Options(useGeneratedKeys = true, keyProperty = "billId")
	@Insert({ //
			"insert into bill (bill_num, user_id, food_id, order_time, amount, quantity)", //
			"values (#{billNum}, #{userId}, #{foodId}, #{orderTime}, #{amount}, #{quantity})",//
	})
	int insert(Bill bill);
}
