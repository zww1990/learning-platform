package com.runoob.design.chapter4.javaee.pattern27;

import com.runoob.design.chapter4.javaee.pattern27.impls.EJBService;
import com.runoob.design.chapter4.javaee.pattern27.impls.JMSService;

/**
 * 创建业务查询服务。
 */
public class BusinessLookUp {
	public BusinessService getBusinessService(String serviceType) {
		if (serviceType.equalsIgnoreCase("EJB")) {
			return new EJBService();
		} else {
			return new JMSService();
		}
	}
}
