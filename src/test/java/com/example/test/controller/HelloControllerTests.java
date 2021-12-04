package com.example.test.controller;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.test.model.UserLogin;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class HelloControllerTests {
	@Resource
	private HelloController controller;
	@Resource
	private ObjectMapper json;

	@Test
	public void testUserLoginAndStaffClock() {
		try {
			System.err.println(
					this.controller.userLoginAndStaffClock(new UserLogin().setUserNo("14699").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUserLoginAndStaffClock2() {
		try {
			System.err.println(
					this.controller.userLoginAndStaffClock(new UserLogin().setUserNo("9999").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitStaffClock() {
		try {
			System.err
					.println(this.controller.initStaffClock(new UserLogin().setUserNo("60123").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitStaffClock2() {
		try {
			System.err.println(this.controller.initStaffClock(new UserLogin().setUserNo("9999").setPassword("123456")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitStaffClock3() {
		try {
			System.err.println(json.writerWithDefaultPrettyPrinter().writeValueAsString(
					this.controller.initStaffClock(new UserLogin().setUserNo("100230").setPassword("123456"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectAppStaffClockLogList() {
		try {
			System.err.println(json.writerWithDefaultPrettyPrinter().writeValueAsString(
					controller.selectAppStaffClockLogList(new UserLogin().setUserNo("100230").setPassword("123456"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetAddresses() {
		try {
			System.err.println(json.writerWithDefaultPrettyPrinter().writeValueAsString(controller.getAddresses()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUsers() {
		try {
			System.err
					.println(json.writerWithDefaultPrettyPrinter().writeValueAsString(controller.getUsers().getData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectDeviceList() {
		try {
			System.err.println(json.writerWithDefaultPrettyPrinter()
					.writeValueAsString(controller.selectDeviceList(new UserLogin().setUserNo("100222")).getData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testResetBindDevice() {
		try {
			System.err.println(json.writerWithDefaultPrettyPrinter()
					.writeValueAsString(controller.resetBindDevice("100222", 157)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
