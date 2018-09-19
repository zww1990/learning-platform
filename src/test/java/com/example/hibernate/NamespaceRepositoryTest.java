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
import com.demo.domain.Namespace;
import com.demo.repository.NamespaceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class NamespaceRepositoryTest {
	@Resource
	private NamespaceRepository repository;

	@Test
	public void testSave() {
		try {
			Namespace namespace = new Namespace();
			namespace.setAppId("appId");
			namespace.setClusterName("clusterName");
			namespace.setNamespaceName("namespaceName");
			namespace.setDeleted(false);
			namespace.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(namespace));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Commit
	public void testQuery() {
		try {
			System.err.println(this.repository.findByAppIdAndClusterNameOrderByIdAsc("appId", "clusterName"));
			System.err.println(
					this.repository.findByAppIdAndClusterNameAndNamespaceName("appId", "clusterName", "namespaceName"));
			System.err.println(this.repository.findByAppIdAndNamespaceNameOrderByIdAsc("appId", "namespaceName"));
			System.err.println(this.repository.findByNamespaceName("namespaceName", PageRequest.of(0, 10)));
			System.err.println(this.repository.countByNamespaceNameAndAppIdNot("namespaceName", "appId"));
			this.repository.delete(this.repository.getOne(1L));
			System.err.println(this.repository.batchDelete("appId", "clusterName", "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
