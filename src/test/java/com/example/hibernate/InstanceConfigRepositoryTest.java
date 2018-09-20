package com.example.hibernate;

import java.util.Date;
import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.InstanceConfig;
import com.demo.repository.InstanceConfigRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InstanceConfigRepositoryTest {
	@Resource
	private InstanceConfigRepository repository;

	@Test
	public void testSave() {
		try {
			InstanceConfig entity = new InstanceConfig();
			entity.setConfigAppId("configAppId");
			entity.setConfigClusterName("configClusterName");
			entity.setConfigNamespaceName("configNamespaceName");
			entity.setReleaseKey("releaseKey");
			entity.setReleaseDeliveryTime(new Date());
			System.err.println(this.repository.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Commit
	public void testQuery() {
		try {
			System.err.println(this.repository.findByInstanceIdAndConfigAppIdAndConfigNamespaceName(0, "configAppId",
					"configNamespaceName"));
			System.err.println(this.repository.findByReleaseKeyAndDataChangeLastModifiedTimeAfter("releaseKey",
					new Date(), PageRequest.of(0, 10)));
			System.err.println(this.repository
					.findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfter(
							"appId", "clusterName", "namespaceName", new Date(), PageRequest.of(0, 10)));
			System.err.println(this.repository
					.findByConfigAppIdAndConfigClusterNameAndConfigNamespaceNameAndDataChangeLastModifiedTimeAfterAndReleaseKeyNotIn(
							"appId", "clusterName", "namespaceName", new Date(), Set.of("releaseKey")));
			System.err.println(this.repository.findInstanceIdsByNamespaceAndInstanceAppId("instanceAppId",
					"configAppId", "clusterName", "namespaceName", new Date(), PageRequest.of(0, 10)));
//			System.err.println(this.repository.batchDelete("appId", "clusterName", "namespaceName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
