package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonDao {
	@Select("${sql}")
	List<Map<String, Object>> select(@Param("sql") String sql);
}
