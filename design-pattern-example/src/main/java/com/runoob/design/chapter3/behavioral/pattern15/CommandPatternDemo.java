package com.runoob.design.chapter3.behavioral.pattern15;

import com.runoob.design.chapter3.behavioral.pattern15.impls.BuyStock;
import com.runoob.design.chapter3.behavioral.pattern15.impls.SellStock;

/**
 * 命令模式（Command Pattern）
 */
public class CommandPatternDemo {
	/**
	 * 使用 Broker 类来接受并执行命令。
	 */
	public static void main(String[] args) {
		Stock abcStock = new Stock();

		BuyStock buyStockOrder = new BuyStock(abcStock);
		SellStock sellStockOrder = new SellStock(abcStock);

		Broker broker = new Broker();
		broker.takeOrder(buyStockOrder);
		broker.takeOrder(sellStockOrder);

		broker.placeOrders();
	}
}
