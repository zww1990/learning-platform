package com.example.hello.service;

import java.util.List;

import com.example.hello.config.ApplicationProperties.Address;
import com.example.hello.config.ApplicationProperties.UserInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;

/**
 * HelloService
 * 
 * @author zhangweiwei
 * @date 2021年5月25日,下午4:15:00
 */
public interface HelloService {
	ResponseBody<Boolean> jobSwitch(Boolean enabled);

	ResponseBody<List<UserInfo>> getUsers();

	ResponseBody<List<Address>> getAddresses();

	ResponseBody<?> saveAddress(Address address);

	ResponseBody<?> saveUser(UserInfo userInfo);

	ResponseBody<?> initStaffClock(UserLogin userLogin);

	ResponseBody<?> userLoginAndStaffClockV2(UserLogin userLogin);

	ResponseBody<?> userLoginAndStaffClockV1(UserLogin userLogin);

	ResponseBody<?> selectAppStaffClockLogList(UserLogin userLogin);

	ResponseBody<?> selectDeviceList(UserLogin userLogin);

	ResponseBody<?> resetBindDevice(String staffNo, Integer id);
}