package com.demo.test;

import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.demo.config.SpringConfig;
import com.demo.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AppTest {
	@Inject
	@Named("AServiceImpl")
	private DemoService	aservice;
	@Inject
	@Named("BServiceImpl")
	private DemoService	bservice;

	@Test
	public void test() {
		try {
			System.out.println(this.aservice);
			System.out.println(this.bservice);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
