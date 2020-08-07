package com.stampede.changepwd;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.stampede.changepwd.service.PersonJobService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonJobServiceTests {
	@Resource
	private PersonJobService jobService;

	@Test
	public void testCreateLdapAccountAndSendMail() {
		try {
			this.jobService.createLdapAccountAndSendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
