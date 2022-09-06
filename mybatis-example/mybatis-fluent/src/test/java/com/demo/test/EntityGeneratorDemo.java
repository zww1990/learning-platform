package com.demo.test;

import org.junit.jupiter.api.Test;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;

public class EntityGeneratorDemo {
	// 数据源 url
	static final String url = "jdbc:mysql://192.168.32.33:3306/seata_client?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
	// 数据库用户名
	static final String username = "zww";
	// 数据库密码
	static final String password = "1qaz2wsx";

	@Test
	public void testGenerate() {
		try {
			// 引用配置类，build方法允许有多个配置类
			FileGenerator.build(Empty.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Tables(
			// 设置数据库连接信息
			url = url, username = username, password = password,
			// 设置entity类生成src目录, 相对于 user.dir
			srcDir = "src/main/java",
			// 设置entity类的package值
			basePack = "cn.demo.entity",
			// 设置dao接口和实现的src目录, 相对于 user.dir
			daoDir = "src/main/java",
			// 设置哪些表要生成Entity文件
			tables = { @Table(value = { "client_user" }) })
	static class Empty { // 类名随便取, 只是配置定义的一个载体
	}
}
