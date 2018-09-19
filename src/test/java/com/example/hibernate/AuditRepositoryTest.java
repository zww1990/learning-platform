package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Audit;
import com.demo.repository.AuditRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AuditRepositoryTest {
	@Resource
	private AuditRepository repository;

	@Test
	public void testSave() {
		try {
			Audit audit = new Audit();
			audit.setEntityName("entityName");
			audit.setOpName("opName");
			audit.setDeleted(true);
			audit.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(audit));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findByOwner("owner"));
			System.err.println(this.repository.findAudits("owner", "entity", "op"));
			this.repository.delete(this.repository.getOne(1L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
