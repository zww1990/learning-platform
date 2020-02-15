package com.stampede.changepwd;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.query.LdapQueryBuilder;
import com.stampede.changepwd.repository.PersonRepository;

@SpringBootTest
public class PersonRepositoryTests {
	@Resource
	private PersonRepository personRepository;

	@Test
	public void test() {
		try {
			this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is("zhangweiwei1")).ifPresent(c -> {
				System.err.println(c.getCname());
				System.err.println(c.getGidNumber());
				System.err.println(c.getGivenName());
				System.err.println(c.getId());
				System.err.println(c.getMail());
				System.err.println(c.getSname());
				System.err.println(c.getUid());
				System.err.println(c.getUidNumber());
				System.err.println(c.getUserPassword() + " -->> " + new String(c.getUserPassword()));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
