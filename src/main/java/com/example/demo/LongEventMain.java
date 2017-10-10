package com.example.demo;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:30:49
 * @description
 */
public class LongEventMain {
	public static void main(String[] args) throws Exception {
		// 将用于为消费者构建新线程的执行程序
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		// 工厂为事件
		LongEventFactory factory = new LongEventFactory();
		// 指定环形缓冲区的大小，必须是2的幂。
		int bufferSize = 1024;
		// 构建Disruptor
		Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, threadFactory);
		// 连接处理程序
		disruptor.handleEventsWith(new LongEventHandler());
		// 启动Disruptor，启动运行的所有线程
		disruptor.start();
		// 从Disruptor获取环形缓冲区，用于发布。
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
		ByteBuffer bb = ByteBuffer.allocate(8);
		for (long l = 0; true; l++) {
			bb.putLong(0, l);
			producer.onData(bb);
			Thread.sleep(1000);
		}
	}
}
