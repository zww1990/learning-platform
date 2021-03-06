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
			this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is("")).ifPresent(c -> {
				System.err.println(c.getCname());
				System.err.println(c.getGidNumber());
				System.err.println(c.getGivenName());
				System.err.println(c.getId());
				System.err.println(c.getMail());
				System.err.println(c.getSname());
				System.err.println(c.getUid());
				System.err.println(c.getUidNumber());
				String[] nums = c.getUserPassword().split(",");
				byte[] bytes = new byte[nums.length];
				for (int i = 0; i < bytes.length; i++) {
					bytes[i] = Byte.parseByte(nums[i]);
				}
				System.err.println(new String(bytes));
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSave() {
		try {
			Optional<Person> optional = this.personRepository
					.findOne(LdapQueryBuilder.query().where("uid").is(""));
			if (optional.isPresent()) {
				Person person = optional.get();
				person.setUserPassword(LdapPasswordUtils.md5Password(""));
				this.personRepository.save(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMD5() {
		try {
			System.err.println(LdapPasswordUtils.md5Password(""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
