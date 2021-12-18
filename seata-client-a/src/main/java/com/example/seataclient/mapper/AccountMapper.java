package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.seataclient.domain.Account;

@Mapper
public interface AccountMapper {
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	@Insert("insert into account (user_name, balance) values (#{userName}, #{balance})")
	int insert(Account account);

	@Update("update account set user_name=#{userName}, balance=balance - #{balance} where user_id=#{userId}")
	int update(Account account);

	@Select("select * from account where user_id=#{userId}")
	Account selectByUserId(@Param("userId") Integer userId);
}
