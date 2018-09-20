package com.example.hibernate;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.ReleaseHistory;
import com.demo.repository.ReleaseHistoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReleaseHistoryRepositoryTest {
	@Resource
	private ReleaseHistoryRepository repository;

	@Test
	public void testSave() {
		try {
			ReleaseHistory entity = new ReleaseHistory();
			entity.setAppId("appId");
			entity.setClusterName("clusterName");
			entity.setNamespaceName("namespaceName");
			entity.setBranchName("branchName");
			entity.setReleaseId(0);
			entity.setPreviousReleaseId(0);
			entity.setOperation(0);
			entity.setOperationContext("operationContext");
			entity.setDeleted(false);
			entity.setDataChangeCreatedBy("dataChangeCreatedBy");
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
			System.err.println(this.repository.findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc("appId",
					"clusterName", "namespaceName", PageRequest.of(0, 10)));
			System.err.println(this.repository.findByReleaseIdAndOperationOrderByIdDesc(0, 0, PageRequest.of(0, 10)));
			System.err.println(
					this.repository.findByPreviousReleaseIdAndOperationOrderByIdDesc(0, 0, PageRequest.of(0, 10)));
//			this.repository.delete(this.repository.getOne(1L));
//			System.err.println(this.repository.batchDelete("appId", "clusterName", "namespaceName", "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
