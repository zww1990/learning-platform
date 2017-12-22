package com.demo.service.impl;

import java.util.List;
import javax.inject.Named;
import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.service.DemoService;
import com.wiwj.bdm.base.api.ApiShopService;
import com.wiwj.bdm.base.dto.page.ShopParam;
import com.wiwj.bdm.base.excpetion.ApiException;
import com.wiwj.bdm.base.po.Shop;

@Named
public class AServiceImpl implements DemoService {
	@Reference(version = "1.0.0")
	private ApiShopService apiShopService;

	@Override
	public List<Shop> queryShopMaps(ShopParam param) throws ApiException {
		return this.apiShopService.queryShopMaps(param);
	}
}
