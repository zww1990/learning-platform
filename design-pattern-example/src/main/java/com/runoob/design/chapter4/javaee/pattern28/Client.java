package com.runoob.design.chapter4.javaee.pattern28;

import java.util.Arrays;

/**
 * 创建使用组合实体的客户端类。
 */
public class Client {
	private CompositeEntity compositeEntity = new CompositeEntity();

	public void printData() {
		Arrays.stream(compositeEntity.getData()).forEach(s -> {
			System.out.println("Data: " + s);
		});
	}

	public void setData(String data1, String data2) {
		compositeEntity.setData(data1, data2);
	}
}
