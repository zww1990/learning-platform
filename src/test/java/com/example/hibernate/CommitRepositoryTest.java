package com.example.hibernate;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Commit;
import com.demo.repository.CommitRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CommitRepositoryTest {
	@Resource
	private CommitRepository repository;

	@Test
	public void testSave() {
		try {
			Commit commit = new Commit();
			commit.setChangeSets("changeSets");
			commit.setAppId("appId");
			commit.setClusterName("clusterName");
			commit.setNamespaceName("namespaceName");
			commit.setComment("comment");
			commit.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(commit));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@org.springframework.test.annotation.Commit
	public void testQuery() {
		try {
			System.err.println(this.repository.findByAppIdAndClusterNameAndNamespaceNameOrderByIdDesc("appId",
					"clusterName", "namespaceName", PageRequest.of(0, 10)));
//			this.repository.delete(this.repository.getOne(4L));
//			System.err.println(this.repository.batchDelete("appId", "clusterName", "namespaceName", "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
