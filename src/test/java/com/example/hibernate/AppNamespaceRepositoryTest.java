package com.example.hibernate;

import java.util.Set;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.AppNamespace;
import com.demo.repository.AppNamespaceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AppNamespaceRepositoryTest {
	@Resource
	private AppNamespaceRepository repository;

	@Test
	public void testSave() {
		try {
			AppNamespace entity = new AppNamespace();
			entity.setName("name");
			entity.setAppId("appId");
			entity.setFormat("format");
			entity.setPublic(true);
			entity.setComment("comment");
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
			System.err.println(this.repository.findByAppIdAndName("appId", "name"));
			System.err.println(this.repository.findByAppIdAndNameIn("appId", Set.of("name")));
			System.err.println(this.repository.findByNameAndIsPublicTrue("name"));
			System.err.println(this.repository.findByNameInAndIsPublicTrue(Set.of("name")));
			System.err.println(this.repository.findByAppIdAndIsPublic("appId", true));
			System.err.println(this.repository.findByAppId("appId"));
			System.err.println(this.repository.findFirst500ByIdGreaterThanOrderByIdAsc(0));
//			this.repository.delete(this.repository.getOne(1L));
//			System.err.println(this.repository.batchDeleteByAppId("appId", "operator"));
//			System.err.println(this.repository.delete("appId", "name", "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
