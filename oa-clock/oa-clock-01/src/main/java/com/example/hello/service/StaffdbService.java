package com.example.hello.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.example.hello.model.ResponseBody;

import cn.net.yzl.oa.entity.vo.AppStaffClockVO;

/**
 * StaffdbService
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月2日,下午6:05:22
 */
public interface StaffdbService {
	@GetExchange("/appStaffClockLog/initStaffClock")
	ResponseBody<Map<String, Object>> initStaffClock(//
			@RequestParam String staffNo, //
			@RequestHeader Map<String, ?> header);

	@PostExchange("/appDeviceRecord/resetBindDeviceId")
	ResponseBody<?> resetBindDeviceId(//
			@RequestParam Map<String, ?> param, //
			@RequestHeader Map<String, ?> header);

	@GetExchange("/appDeviceRecord/getDeviceList")
	ResponseBody<?> getDeviceList(//
			@RequestParam String staffNo, //
			@RequestParam Integer pageNo, //
			@RequestParam Integer pageSize, //
			@RequestHeader Map<String, ?> header);

	@PostExchange("/appStaffClockLog/staffClock")
	ResponseBody<?> staffClock(//
			@RequestBody AppStaffClockVO vo, //
			@RequestHeader Map<String, ?> header);

	@GetExchange("/appStaffClockLog/createYesterdayOaAttendTest")
	ResponseBody<?> createYesterdayOaAttendTest(//
			@RequestParam String staffNos, //
			@RequestParam String stratTime, //
			@RequestParam String endTime, //
			@RequestHeader Map<String, ?> header);
}
