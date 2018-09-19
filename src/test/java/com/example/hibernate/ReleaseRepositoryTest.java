package com.example.hibernate;

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
import com.demo.domain.Release;
import com.demo.repository.ReleaseRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReleaseRepositoryTest {
	@Resource
	private ReleaseRepository repository;

	@Test
	public void testSave() {
		try {
			Release release = new Release();
			release.setReleaseKey("releaseKey");
			release.setName("name");
			release.setComment("comment");
			release.setAppId("appId");
			release.setClusterName("clusterName");
			release.setNamespaceName("namespaceName");
			release.setConfigurations("configurations");
			release.setAbandoned(false);
			release.setDeleted(false);
			release.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(release));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Commit
	public void testQuery() {
		try {
			System.err.println(
					this.repository.findFirstByAppIdAndClusterNameAndNamespaceNameAndIsAbandonedFalseOrderByIdDesc(
							"appId", "clusterName", "namespaceName"));
			System.err.println(this.repository.findByIdAndIsAbandonedFalse(2));
			System.err.println(this.repository.findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc("appId",
					"clusterName", "namespaceName", PageRequest.of(0, 10)));
			System.err.println(
					this.repository.findByAppIdAndClusterNameAndNamespaceNameAndIsAbandonedFalseOrderByIdDesc("appId",
							"clusterName", "namespaceName", PageRequest.of(0, 10)));
			System.err.println(this.repository.findByReleaseKeyIn(Set.of("releaseKey")));
			System.err.println(this.repository.findByIdIn(Set.of(2L)));
			System.err.println(this.repository.findByAppIdAndClusterNameAndNamespaceNameOrderByIdAsc("appId",
					"clusterName", "namespaceName"));
			this.repository.delete(this.repository.getOne(2L));
			System.err.println(this.repository.batchDelete("appId", "clusterName", "namespaceName", "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
