package com.example.hello.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.config.ApplicationProperties.Address;
import com.example.hello.config.ApplicationProperties.UserInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.HelloService;

/**
 * HelloController
 * 
 * @author zhang weiwei
 * @since 2022年8月13日,下午8:57:43
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
	@Resource
	private HelloService helloService;

	@GetMapping("/users")
	public ResponseBody<List<UserInfo>> getUsers() {
		return this.helloService.getUsers();
	}

	@GetMapping("/addresses")
	public ResponseBody<List<Address>> getAddresses() {
		return this.helloService.getAddresses();
	}

	@PostMapping("/saveaddress")
	public ResponseBody<?> saveAddress(@RequestBody Address address) {
		return this.helloService.saveAddress(address);
	}

	@PostMapping("/initstaffclock")
	public ResponseBody<?> initStaffClock(@RequestBody UserLogin userLogin) {
		return this.helloService.initStaffClock(userLogin);
	}

	@PostMapping("/v2/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV2(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV2(userLogin);
	}

	@PostMapping("/v1/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV1(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV1(userLogin);
	}

	@PostMapping("/selectappstaffclockloglist")
	public ResponseBody<?> selectAppStaffClockLogList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectAppStaffClockLogList(userLogin);
	}

	@PostMapping("/selectdevicelist")
	public ResponseBody<?> selectDeviceList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectDeviceList(userLogin);
	}

	@PostMapping("/resetbinddevice")
	public ResponseBody<?> resetBindDevice(@RequestParam String staffNo, @RequestParam Integer id) {
		return this.helloService.resetBindDevice(staffNo, id);
	}
}
