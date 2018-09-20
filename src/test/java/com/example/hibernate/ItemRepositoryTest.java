package com.example.hibernate;

import java.util.Date;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Item;
import com.demo.repository.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ItemRepositoryTest {
	@Resource
	private ItemRepository repository;

	@Test
	public void testSave() {
		try {
			Item item = new Item();
			item.setNamespaceId(0);
			item.setKey("key");
			item.setValue("value");
			item.setDeleted(false);
			item.setDataChangeCreatedBy("dataChangeCreatedBy");
			System.err.println(this.repository.save(item));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	@Commit
	public void testQuery() {
		try {
			System.err.println(this.repository.findByNamespaceIdAndKey(0L, "key"));
			System.err.println(this.repository.findByNamespaceIdOrderByLineNumAsc(0L));
			System.err.println(this.repository.findByNamespaceId(0L));
			System.err
					.println(this.repository.findByNamespaceIdAndDataChangeLastModifiedTimeGreaterThan(0L, new Date()));
			System.err.println(this.repository.findFirst1ByNamespaceIdOrderByLineNumDesc(0L));
//			this.repository.delete(this.repository.getOne(2L));
//			System.err.println(this.repository.deleteByNamespaceId(0, "operator"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
