package com.example.mybatisfluent;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mybatisfluent.dao.HelloWorldDao;
import com.example.mybatisfluent.entity.HelloWorld;
import com.example.mybatisfluent.mapper.HelloWorldMapper;

@SpringBootTest
public class MybatisFluentApplicationTests {
	@Autowired
	private HelloWorldDao helloWorldDao;
	@Autowired
	private HelloWorldMapper helloWorldMapper;

	@Test
	public void testSave() {
		try {
			this.helloWorldDao.deleteAll();
			this.helloWorldMapper.deleteByMapAndDefault(Collections.emptyMap());
			this.helloWorldDao.saveOne(new HelloWorld().setSayHello("hello world 1").setYourName("张三"));
			this.helloWorldMapper.insert(new HelloWorld().setSayHello("hello world 2").setYourName("李四"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testContextLoads() {
		try {
			System.err.println(this.helloWorldDao.selectAll().size());
			System.err.println(this.helloWorldMapper.listByMapAndDefault(Collections.emptyMap()).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
