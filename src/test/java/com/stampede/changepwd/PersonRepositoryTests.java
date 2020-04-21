package com.stampede.changepwd;

import java.util.Optional;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.repository.PersonRepository;
import com.stampede.changepwd.util.LdapPasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTests {
	@Resource
	private PersonRepository personRepository;

	@Test
	public void testFindOne() {
		try {
			this.personRepository.findOne(LdapQueryBuilder.query().where("uid").is("zhaochenyu")).ifPresent(c -> {
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
					.findOne(LdapQueryBuilder.query().where("uid").is("zhangweiwei1"));
			if (optional.isPresent()) {
				Person person = optional.get();
				person.setUserPassword(LdapPasswordUtils.md5Password("1q2w3e4r"));
				this.personRepository.save(person);
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
}
