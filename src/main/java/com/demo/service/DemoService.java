package com.demo.service;

import java.util.List;
import com.wiwj.bdm.base.dto.page.ShopParam;
import com.wiwj.bdm.base.excpetion.ApiException;
import com.wiwj.bdm.base.po.Shop;

public interface DemoService {
	List<Shop> queryShopMaps(ShopParam param) throws ApiException;
}
