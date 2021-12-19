package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.seataclient.domain.Account;

/**
 * 账户持久化接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午4:51:26
 */
@Mapper
public interface AccountMapper {
	/**
	 * 插入记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:52:15
	 * @param account 账户
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "userId")
	@Insert("insert into account (user_name, balance) values (#{userName}, #{balance})")
	int insert(Account account);

	/**
	 * 更新记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:52:18
	 * @param account 账户
	 * @return
	 */
	@Update("update account set user_name=#{userName}, balance=balance - #{balance} where user_id=#{userId}")
	int update(Account account);

	/**
	 * 按主键查询单条记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午4:52:21
	 * @param userId 主键
	 * @return
	 */
	@Select("select * from account where user_id=#{userId}")
	Account selectByUserId(@Param("userId") Integer userId);
}
