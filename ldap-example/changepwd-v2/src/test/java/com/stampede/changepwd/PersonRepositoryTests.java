package com.stampede.changepwd;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.query.LdapQueryBuilder;

import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.repository.PersonRepository;
import com.stampede.changepwd.util.LdapPasswordUtils;

@SpringBootTest
public class PersonRepositoryTests {
	@Resource
	private PersonRepository personRepository;

	@Test
	public void testFindOne() {
		try {
			this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is("alienware")).ifPresent(c -> {
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

	@Test
	public void testSave() {
		try {
			Optional<Person> optional = this.personRepository
					.findOne(LdapQueryBuilder.query().where("uid").is("alienware"));
			if (optional.isPresent()) {
				Person person = optional.get();
				person.setUserPassword(LdapPasswordUtils.md5Password("hello"));
				this.personRepository.save(person);
				System.err.println("well done...");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMD5() {
		try {
			System.err.println(LdapPasswordUtils.md5Password("1q2w3e4r"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		try {
			this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is("jiratest")).ifPresent(c -> {
				this.personRepository.delete(c);
				System.err.println("well done...");
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
