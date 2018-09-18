package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Serverconfig;
import com.demo.repository.ServerconfigDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class ServerconfigDaoTest {
	@Resource
	private ServerconfigDao serverconfigDao;

	@Test
	public void testSave() {
		try {
			Serverconfig entity = new Serverconfig();
			entity.setKey("key");
			entity.setCluster("cluster");
			entity.setValue("value");
			entity.setComment("comment");
			entity.setDatachangeCreatedby("datachangeCreatedby");
			entity.setDeleted(true);
			System.err.println(this.serverconfigDao.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
