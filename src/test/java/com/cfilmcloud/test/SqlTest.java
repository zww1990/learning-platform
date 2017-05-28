package com.cfilmcloud.test;

import java.util.Date;

import org.junit.Test;

import com.cfilmcloud.nebula.crawler.dao.mapper.TSysDsConfigSqlProvider;
import com.cfilmcloud.nebula.crawler.model.TSysDsConfig;

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
}
