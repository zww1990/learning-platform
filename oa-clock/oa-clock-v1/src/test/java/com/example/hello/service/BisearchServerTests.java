package com.example.hello.service;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.hello.config.ApplicationProperties;

import cn.net.yzl.oa.entity.SqlExecQueryDTO;

/**
 * BisearchServerTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年12月2日,下午6:31:05
 */
@SpringBootTest
public class BisearchServerTests {
	@Autowired
	private BisearchServer bisearchServer;
	@Autowired
	private ApplicationProperties properties;

	@Test
	public void testBisqlExec() {
		try {
			SqlExecQueryDTO dto = new SqlExecQueryDTO();
			dto.setSourceId(this.properties.getBiSqlSourceId());
			dto.setCommand("SELECT id,staff_no,clock_time FROM app_staff_clock_log limit 10");
			Map<String, String> header = Map.of("appid", "oa");
			System.err.println(this.bisearchServer.bisqlExec(dto, header));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
