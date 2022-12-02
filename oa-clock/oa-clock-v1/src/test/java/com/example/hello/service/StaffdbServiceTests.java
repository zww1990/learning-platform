package com.example.hello.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hello.config.ApplicationProperties;
import com.example.hello.config.ApplicationProperties.Address;

import cn.net.yzl.oa.entity.vo.AppStaffClockVO;

/**
 * StaffdbServiceTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月2日,下午6:31:00
 */
@SpringBootTest
public class StaffdbServiceTests {
	@Autowired
	private StaffdbService staffdbService;
	@Autowired
	private ApplicationProperties properties;

	@Test
	public void testInitStaffClock() {
		try {
			Map<String, ?> header = Map.of("appid", "oa");
			System.err.println(this.staffdbService.initStaffClock("100230", header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testResetBindDeviceId() {
		try {
			Map<String, ?> header = Map.of("appid", "oa");
			Map<String, Object> param = new HashMap<>();
			param.put("staffNo", "100230");
			param.put("id", "123");
			System.err.println(this.staffdbService.resetBindDeviceId(param, header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetDeviceList() {
		try {
			Map<String, ?> header = Map.of("appid", "oa");
			System.err.println(this.staffdbService.getDeviceList("100230", 1, 10, header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStaffClock() {
		try {
			Map<String, ?> header = Map.of("appid", "oa");
			Address addr = this.properties.getAddresses().get(0);
			ThreadLocalRandom random = ThreadLocalRandom.current();
			AppStaffClockVO vo = new AppStaffClockVO()//
					.setAddress(addr.getAddress())//
					.setLatitude(addr.getLatitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
							RoundingMode.HALF_UP))//
					.setLongitude(addr.getLongitude().add(BigDecimal.valueOf(random.nextFloat())).setScale(7,
							RoundingMode.HALF_UP))//
					.setStaffNo("100230")//
					.setClockTime(new Date());
			System.err.println(this.staffdbService.staffClock(vo, header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateYesterdayOaAttendTest() {
		try {
			Map<String, ?> header = Map.of("appid", "oa");
			System.err.println(this.staffdbService.createYesterdayOaAttendTest("100230", "", "", header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
