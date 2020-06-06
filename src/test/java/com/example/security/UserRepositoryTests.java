package com.example.security;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.security.model.Authority;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {
	@Resource
	private UserRepository userdao;
	@Resource
	private PasswordEncoder encoder;

	@Test
	public void testSave() {
		try {
			Authority a1 = new Authority("超级管理员", "ROLE_ADMIN");

			Authority a2 = new Authority("访客", "ROLE_GUEST");

			User u1 = new User("admin", this.encoder.encode("admin"), true, true, true, true, Arrays.asList(a1));
			this.userdao.save(u1);

			User u2 = new User("guest", this.encoder.encode("guest"), true, true, true, true, Arrays.asList(a2));
			this.userdao.save(u2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
