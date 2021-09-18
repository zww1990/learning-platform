package com.stampede.changepwd;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.stampede.changepwd.service.PersonJobService;

@SpringBootTest
public class PersonJobServiceTests {
	@Resource
	private PersonJobService jobService;

	@Test
	public void testCreateLdapAccountAndSendMail() {
		try {
//			this.jobService.firstCreateLdapAccountAndSendMail();
//			this.jobService.secondCreateLdapAccountAndSendMail();
			this.jobService.thirdCreateLdapAccountAndSendMail();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
