package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.App;
import com.demo.repository.AppRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AppRepositoryTest {
	@Resource
	private AppRepository repository;

	@Test
	public void testSave() {
		try {
			App app = new App();
			app.setAppId("appId");
			app.setName("name");
			app.setOrgId("orgId");
			app.setOrgName("orgName");
			app.setOwnerName("ownerName");
			app.setOwnerEmail("ownerEmail");
			app.setDeleted(true);
			app.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(app));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findByName("name"));
			System.err.println(this.repository.findByAppId("appId"));
			this.repository.delete(this.repository.getOne(1L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
