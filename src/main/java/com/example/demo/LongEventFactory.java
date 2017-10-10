package com.example.demo;

import com.lmax.disruptor.EventFactory;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:21:10
 * @description 为了让Disruptor为我们预先分配这些事件，我们需要一个将执行构建的EventFactory
 */
public class LongEventFactory implements EventFactory<LongEvent> {

	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}

}
