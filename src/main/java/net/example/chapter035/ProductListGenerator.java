package net.example.chapter035;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
	public List<Product> generate(int size) {
		List<Product> list = new ArrayList<Product>();
		for (int i = 0; i < size; i++) {
			list.add(new Product("Product " + i, 10));
		}
		return list;
	}
}
