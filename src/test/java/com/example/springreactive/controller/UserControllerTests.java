package com.example.springreactive.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springreactive.model.ClientUser;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * UserControllerTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年4月26日,下午5:17:49
 */
@SpringBootTest
public class UserControllerTests {
	@Resource
	private UserController controller;
	@Resource
	private ObjectMapper json;

	@Test
	public void testGetClientUser() {
		try {
			System.err.println(this.controller.getClientUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddClientUser() {
		try {
			ClientUser user = new ClientUser().setGender(1).setPhoneNumber("1346579").setUserId("1001")
					.setUsername("张三");
			System.err.println(this.json.writerWithDefaultPrettyPrinter().writeValueAsString(user));
			System.err.println(this.controller.addClientUser(user));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
