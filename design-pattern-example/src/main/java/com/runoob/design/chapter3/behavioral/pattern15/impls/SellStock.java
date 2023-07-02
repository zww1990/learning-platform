package com.runoob.design.chapter3.behavioral.pattern15.impls;

import com.runoob.design.chapter3.behavioral.pattern15.Order;
import com.runoob.design.chapter3.behavioral.pattern15.Stock;

/**
 * 出售
 */
public class SellStock implements Order {
	private Stock abcStock;

	public SellStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	@Override
	public void execute() {
		abcStock.sell();
	}

}
