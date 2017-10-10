package com.example.demo;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:20:05
 * @description 要开始使用Disruptor，我们将考虑一个非常简单而有创意的例子，<br>
 *              一个将生产者的一个长期价值从消费者那里传递出去，消费者将简单的打印出价值。 <br>
 * 				首先我们将定义将携带数据的事件。
 */
public class LongEvent {
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
}
