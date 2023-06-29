package com.example.shardingreadwritesplitting.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.shardingreadwritesplitting.domain.User;

@Mapper
public interface UserRepository {

	@Insert("insert into t_user(nickname, password, sex, birthday) values(#{nickname}, #{password}, #{sex}, #{birthday})")
	int insertUser(User user);

	@Select("select * from t_user")
	List<User> selectUsers();
}
