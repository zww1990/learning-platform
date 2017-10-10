package com.example.demo;

import com.lmax.disruptor.EventHandler;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:22:18
 * @description 一旦我们定义了事件，我们需要创建一个处理这些事件的消费者。 在我们的例子中，我们要做的就是把控制台的值打印出来。
 */
public class LongEventHandler implements EventHandler<LongEvent> {

	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.err.println("Event: " + event.getValue());
	}

}
