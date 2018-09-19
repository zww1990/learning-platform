package com.example.hibernate;

import java.util.Date;
import java.util.Set;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.ReleaseMessage;
import com.demo.repository.ReleaseMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ReleaseMessageRepositoryTest {
	@Resource
	private ReleaseMessageRepository repository;

	@Test
	public void testSave() {
		try {
			ReleaseMessage entity = new ReleaseMessage();
			entity.setMessage("message");
			entity.setDataChangeLastModifiedTime(new Date());
			System.err.println(this.repository.save(entity));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			System.err.println(this.repository.findFirst500ByIdGreaterThanOrderByIdAsc(0L));
			System.err.println(this.repository.findTopByOrderByIdDesc());
			System.err.println(this.repository.findTopByMessageInOrderByIdDesc(Set.of("message")));
			System.err.println(this.repository.findFirst100ByMessageAndIdLessThanOrderByIdAsc("message", 0L));
			System.err.println(this.repository.findLatestReleaseMessagesGroupByMessages(Set.of("message")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
