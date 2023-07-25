package com.example.mybatisfluent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.mybatisfluent.entity.HelloWorld;

@Mapper
public interface HelloWorldDao {
	@Select("select * from hello_world")
	List<HelloWorld> selectAll();

	@Insert("""
			insert into hello_world (say_hello, your_name, gmt_created, gmt_modified)
			 values (#{sayHello}, #{yourName}, now(), now())
			""")
	int saveOne(HelloWorld entity);

	@Delete("delete from hello_world")
	int deleteAll();

}
