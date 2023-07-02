package com.runoob.design.chapter3.behavioral.pattern15.impls;

import com.runoob.design.chapter3.behavioral.pattern15.Order;
import com.runoob.design.chapter3.behavioral.pattern15.Stock;

/**
 * 购买
 */
public class BuyStock implements Order {
	private Stock abcStock;

	public BuyStock(Stock abcStock) {
		this.abcStock = abcStock;
	}

	@Override
	public void execute() {
		abcStock.buy();
	}

}
