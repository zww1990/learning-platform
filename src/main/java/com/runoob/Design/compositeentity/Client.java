package com.runoob.Design.compositeentity;

import java.util.Arrays;

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
