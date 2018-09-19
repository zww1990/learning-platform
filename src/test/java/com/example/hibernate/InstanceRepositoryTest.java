package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Instance;
import com.demo.repository.InstanceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InstanceRepositoryTest {
	@Resource
	private InstanceRepository repository;

	@Test
	public void testSave() {
		try {
			Instance instance = new Instance();
			instance.setAppId("appId");
			instance.setClusterName("clusterName");
			instance.setDataCenter("dataCenter");
			instance.setIp("ip");
			System.err.println(this.repository.save(instance));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findByAppIdAndClusterNameAndDataCenterAndIp("appId", "clusterName",
					"dataCenter", "ip"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
