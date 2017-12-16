package com.demo.test;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.demo.nebula.crawler.dao.mapper.TSysDsConfigSqlProvider;
import com.demo.nebula.crawler.model.TSysDsConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SqlTest {
	@Test
	public void testSql() {
		try {
			TSysDsConfigSqlProvider provider = new TSysDsConfigSqlProvider();
			TSysDsConfig record = new TSysDsConfig();
			record.setCreater("zww");
			record.setCreateTime(new Date());
			record.setIsDelete(1);
			String sql = provider.insertSelective(record);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testJson() {
		try {
			ObjectMapper om = new ObjectMapper();
			String parent = "D:\\Projects\\zww\\mybatis\\src\\test\\resources";
			String child = "data.json";
			File file = new File(parent, child);
			Map<String, Object> value = new HashMap<String, Object>();
			value.put("name", "张维维");
			value.put("age", 12);
			value.put("now", new Date());
			value.put("high", 123.23);
			om.writeValue(file, value);
			System.out.println("OK!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
