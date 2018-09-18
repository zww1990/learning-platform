package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Appnamespace;
import com.demo.repository.AppnamespaceDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AppnamespaceDaoTest {
	@Resource
	private AppnamespaceDao appnamespaceDao;

	@Test
	public void testSave() {
		try {
			Appnamespace entity = new Appnamespace();
			entity.setName("name");
			entity.setAppid("appid");
			entity.setComment("comment");
			entity.setFormat("format");
			entity.setPublic(true);
			entity.setDeleted(true);
			entity.setDatachangeCreatedby("datachangeCreatedby");
			System.err.println(this.appnamespaceDao.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
