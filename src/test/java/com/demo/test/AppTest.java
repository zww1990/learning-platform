package com.demo.test;

import javax.inject.Inject;
import javax.inject.Named;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.demo.config.SpringConfig;
import com.demo.service.DemoService;
import com.wiwj.bdm.base.dto.page.ShopParam;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AppTest {
	@Inject
	@Named("AServiceImpl")
	private DemoService aservice;
	@Inject
	@Named("BServiceImpl")
	private DemoService bservice;

	@Test
	public void test() {
		try {
			ShopParam param = new ShopParam();
			param.setCityCode("110000");
			param.setLatMax(40.010508448D);
			param.setLatMin(39.993098D);
			param.setLngMax(116.484987D);
			param.setLngMin(116.453462333333D);
			param.setStoreStatus(1);
			System.out.println(this.aservice.queryShopMaps(param));
			System.out.println(this.bservice.queryShopMaps(param));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
