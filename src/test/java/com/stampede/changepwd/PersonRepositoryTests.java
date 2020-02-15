package com.stampede.changepwd;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.query.LdapQueryBuilder;
import com.stampede.changepwd.domain.Person;
import com.stampede.changepwd.repository.PersonRepository;

@SpringBootTest
public class PersonRepositoryTests {
	@Resource
	private PersonRepository personRepository;

	@Test
	public void testFindOne() {
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

	@Test
	public void testSave() {
		try {
			Optional<Person> optional = this.personRepository
					.findOne(LdapQueryBuilder.query().where("uid").is("zhangweiwei1"));
			if (optional.isPresent()) {
				Person person = optional.get();
				person.setUserPassword(md5Password("1q2w3e4r"));
				this.personRepository.save(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMD5() {
		try {
			System.err.println(md5Password("1q2w3e4r"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String md5Password(String text) {
		try {
			String algorithm = "MD5";
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			byte[] digest = instance.digest(text.getBytes(StandardCharsets.UTF_8));
			byte[] encode = Base64.getEncoder().encode(digest);
			String password = String.format("{%s}%s", algorithm, new String(encode, StandardCharsets.UTF_8));
			return password;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
