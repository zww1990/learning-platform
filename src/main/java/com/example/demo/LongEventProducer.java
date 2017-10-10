package com.example.demo;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:23:45
 * @description 我们将需要这些事件的源，为了一个例子，我将假定数据来自某种I / O设备，例如 网络或文件以ByteBuffer的形式。
 */
public class LongEventProducer {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	public void onData(ByteBuffer bb) {
		long sequence = ringBuffer.next(); // 抓住下一个序列
		try {
			LongEvent event = ringBuffer.get(sequence); // 获取序列中Disruptor的条目
			event.setValue(bb.getLong(0)); // 填写数据
		} finally {
			ringBuffer.publish(sequence);
		}
	}
}
