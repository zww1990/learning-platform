package com.example.hibernate;

import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.App;
import com.demo.repository.AppDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AppDaoTest {
	@Resource
	private AppDao appDao;

	@Test
	public void testSave() {
		try {
			App app = new App();
			app.setAppid("appid");
			app.setDatachangeCreatedby("datachangeCreatedby");
			app.setDatachangeCreatedtime(new Date());
			app.setName("name");
			app.setOrgid("orgid");
			app.setOrgname("orgname");
			app.setOwneremail("owneremail");
			app.setOwnername("ownername");
			app.setDeleted(true);
			System.err.println(this.appDao.save(app));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			this.appDao.findAll().stream().forEach(x -> {
				System.err.println(x.isDeleted() + "\t" + x.getDatachangeCreatedtime());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
