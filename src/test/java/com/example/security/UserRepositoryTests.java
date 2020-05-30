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
			Authority a1 = new Authority();
			a1.setAuthName("超级管理员");
			a1.setAuthority("ROLE_ADMIN");

			Authority a2 = new Authority();
			a2.setAuthName("访客");
			a2.setAuthority("ROLE_GUEST");

			User u1 = new User();
			u1.setAccountNonExpired(true);
			u1.setAccountNonLocked(true);
			u1.setCredentialsNonExpired(true);
			u1.setEnabled(true);
			u1.setPassword(this.encoder.encode("admin"));
			u1.setUsername("admin");
			u1.setAuthorities(Arrays.asList(a1));
			this.userdao.save(u1);

			User u2 = new User();
			u2.setAccountNonExpired(true);
			u2.setAccountNonLocked(true);
			u2.setCredentialsNonExpired(true);
			u2.setEnabled(true);
			u2.setPassword(this.encoder.encode("guest"));
			u2.setUsername("guest");
			u2.setAuthorities(Arrays.asList(a2));
			this.userdao.save(u2);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
