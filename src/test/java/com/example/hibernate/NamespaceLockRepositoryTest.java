package com.example.hibernate;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.NamespaceLock;
import com.demo.repository.NamespaceLockRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class NamespaceLockRepositoryTest {
	@Resource
	private NamespaceLockRepository repository;

	@Test
	public void testSave() {
		try {
			NamespaceLock entity = new NamespaceLock();
			entity.setNamespaceId(0);
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
			System.err.println(this.repository.findByNamespaceId(0));
			System.err.println(this.repository.deleteByNamespaceId(0L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
