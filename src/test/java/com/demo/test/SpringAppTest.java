package com.demo.test;

import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.dao.UserDao;
import com.demo.dao.UserDynamicSqlSupport;
import com.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringAppTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void testContextLoad() {
		try {
			String[] names = this.context.getBeanDefinitionNames();
			for (int i = 0, len = names.length; i < len; i++) {
				System.err.println((i + 1) + "\t" + names[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Resource
	private UserDao userDao;
	@Resource
	private ObjectMapper objectMapper;

	@Test
	public void testList() {
		try {
			List<User> users = this.userDao.selectByExample().where(UserDynamicSqlSupport.id, SqlBuilder.isIn(1, 2, 3))
					.and(UserDynamicSqlSupport.age, SqlBuilder.isBetween(11).and(22))
					.and(UserDynamicSqlSupport.name, SqlBuilder.isLikeCaseInsensitive("%张%"))
					.orderBy(UserDynamicSqlSupport.birthday.descending(), UserDynamicSqlSupport.id).build().execute();
			System.err.println(this.objectMapper.writeValueAsString(users));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPage() {
		try {
			Page<User> page = PageHelper.startPage(1, 10)
					.doSelectPage(() -> this.userDao.selectByExample().build().execute());
			System.err.println(page);
			PageInfo<User> pageInfo = PageHelper.startPage(1, 10)
					.doSelectPageInfo(() -> this.userDao.selectByExample().build().execute());
			System.err.println(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
