package com.runoob.design.chapter4.javaee.pattern32;

import java.util.ArrayList;
import java.util.List;

/**
 * 缓存
 */
public class Cache {
	private List<Service> services;

	public Cache() {
		services = new ArrayList<>();
	}

	public Service getService(String serviceName) {
		for (Service service : services) {
			if (service.getName().equalsIgnoreCase(serviceName)) {
				System.out.println("Returning cached  " + serviceName
						+ " object");
				return service;
			}
		}
		return null;
	}

	public void addService(Service newService) {
		boolean exists = false;
		for (Service service : services) {
			if (service.getName().equalsIgnoreCase(newService.getName())) {
				exists = true;
			}
		}
		if (!exists) {
			services.add(newService);
		}
	}
}
