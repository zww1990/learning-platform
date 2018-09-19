package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Cluster;
import com.demo.repository.ClusterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ClusterRepositoryTest {
	@Resource
	private ClusterRepository repository;

	@Test
	public void testSave() {
		try {
			Cluster cluster = new Cluster();
			cluster.setName("name");
			cluster.setAppId("appId");
			cluster.setParentClusterId(0);
			cluster.setDeleted(false);
			cluster.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(cluster));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findByAppIdAndParentClusterId("appId", 0L));
			System.err.println(this.repository.findByAppId("appId"));
			System.err.println(this.repository.findByAppIdAndName("appId", "name"));
			System.err.println(this.repository.findByParentClusterId(0));
			this.repository.delete(this.repository.getOne(1L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
