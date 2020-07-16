package com.stampede.changepwd;

import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcTemplateTests {
	@Resource
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testQuery() {
		try {
			String sql = "SELECT user_name, comp_email FROM bdm_user WHERE user_id = ? and comp_email is not null";
			Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, 1000000720);
			for (Entry<String, Object> en : map.entrySet()) {
				System.err.println(en);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
