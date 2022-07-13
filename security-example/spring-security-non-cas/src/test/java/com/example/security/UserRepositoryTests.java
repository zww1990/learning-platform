package com.example.security;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.security.model.Authority;
import com.example.security.model.Menu;
import com.example.security.model.User;
import com.example.security.repository.AuthorityRepository;
import com.example.security.repository.MenuRepository;
import com.example.security.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTests {
	@Resource
	private UserRepository userdao;
	@Resource
	private PasswordEncoder encoder;
	@Resource
	private MenuRepository menudao;
	@Resource
	private AuthorityRepository authdao;

	@Test
	public void testSave() {
		try {
			Authority a1 = new Authority("超级管理员", "ROLE_ADMIN");
			this.authdao.save(a1);

			Authority a2 = new Authority("访客", "ROLE_GUEST");
			this.authdao.save(a2);

			User u1 = new User("admin", this.encoder.encode("admin"), true, true, true, true, Arrays.asList(a1));
			this.userdao.save(u1);

			User u2 = new User("guest", this.encoder.encode("guest"), true, true, true, true, Arrays.asList(a2));
			this.userdao.save(u2);

			Menu m1 = new Menu("管理页面", "/admin/**", true, null, Arrays.asList(a1));
			this.menudao.save(m1);

			Menu m2 = new Menu("访客页面", "/guest/**", true, null, Arrays.asList(a2));
			this.menudao.save(m2);

			Menu m3 = new Menu("测试页面", "/ajax", true, null, Arrays.asList(a1, a2));
			this.menudao.save(m3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
