package dahua.sheji.moshi.chapter08;

import java.util.ArrayList;
import java.util.List;

public class Product {
	private List<String> parts = new ArrayList<>();

	public void add(String part) {
		this.parts.add(part);
	}

	public void show() {
		System.out.println("产品创建----");
		for (String s : parts) {
			System.out.println(s);
		}
	}
}
