package com.example.hello.service;

import java.util.List;

import com.example.hello.config.ApplicationConfig.Address;
import com.example.hello.config.ApplicationConfig.UserInfo;
import com.example.hello.model.JobInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;

/**
 * HelloService
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:16:20
 */
public interface HelloService {
	ResponseBody<List<UserInfo>> getUsers();

	ResponseBody<List<Address>> getAddresses();

	ResponseBody<?> saveAddress(Address address);

	ResponseBody<?> initStaffClock(UserLogin userLogin);

	ResponseBody<?> userLoginAndStaffClockV2(UserLogin userLogin);

	ResponseBody<?> userLoginAndStaffClockV1(UserLogin userLogin);

	ResponseBody<?> userLoginAndStaffClockV3(UserLogin userLogin);

	ResponseBody<?> selectAppStaffClockLogList(UserLogin userLogin);

	ResponseBody<?> selectDeviceList(UserLogin userLogin);

	ResponseBody<?> resetBindDevice(String staffNo, Integer id);

	ResponseBody<?> pauseJob() throws Exception;

	ResponseBody<?> resumeJob() throws Exception;

	ResponseBody<?> saveJob(JobInfo jobInfo) throws Exception;

	ResponseBody<?> triggers() throws Exception;
}
