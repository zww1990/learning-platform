package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.ServerConfig;
import com.demo.repository.ServerConfigRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ServerConfigRepositoryTest {
	@Resource
	private ServerConfigRepository repository;

	@Test
	public void testSave() {
		try {
			ServerConfig entity = new ServerConfig();
			entity.setKey("key");
			entity.setCluster("cluster");
			entity.setValue("value");
			entity.setComment("comment");
			entity.setDeleted(false);
			entity.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findTopByKeyAndCluster("key", "cluster"));
			this.repository.delete(this.repository.getOne(6L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
