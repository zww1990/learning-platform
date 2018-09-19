package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.GrayReleaseRule;
import com.demo.repository.GrayReleaseRuleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GrayReleaseRuleRepositoryTest {
	@Resource
	private GrayReleaseRuleRepository repository;

	@Test
	public void testSave() {
		try {
			GrayReleaseRule entity = new GrayReleaseRule();
			entity.setAppId("appId");
			entity.setClusterName("clusterName");
			entity.setNamespaceName("namespaceName");
			entity.setBranchName("branchName");
			entity.setReleaseId(0L);
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
			System.err.println(this.repository.findTopByAppIdAndClusterNameAndNamespaceNameAndBranchNameOrderByIdDesc(
					"appId", "clusterName", "namespaceName", "branchName"));
			System.err.println(
					this.repository.findByAppIdAndClusterNameAndNamespaceName("appId", "clusterName", "namespaceName"));
			System.err.println(this.repository.findFirst500ByIdGreaterThanOrderByIdAsc(0L));
			this.repository.delete(this.repository.getOne(1L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
