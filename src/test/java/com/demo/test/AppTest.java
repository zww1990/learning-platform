package com.demo.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.config.SpringConfig;
import com.demo.service.DemoService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AppTest {
	@Resource(name = "AServiceImpl")
	private DemoService aservice;
	@Resource(name = "BServiceImpl")
	private DemoService bservice;

	@Test
	public void test() {
		try {
			System.err.println(this.aservice);
			System.err.println(this.bservice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
