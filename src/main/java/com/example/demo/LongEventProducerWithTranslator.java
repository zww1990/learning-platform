package com.example.demo;

import java.nio.ByteBuffer;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * @author ZhangWeiWei
 * @date 2017年10月10日,上午11:28:54
 * @description 使用版本3.0的Disruptor，添加了更丰富的Lambda风格的API，<br>
 *              通过将这种复杂性封装在环形缓冲区中来帮助开发人员，因此3.0之后，<br>
 *              发布消息的首选方法是通过API的事件发布器/事件转换器部分。 例如。
 */
public class LongEventProducerWithTranslator {
	private final RingBuffer<LongEvent> ringBuffer;

	public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}

	private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
		public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
			event.setValue(bb.getLong(0));
		}
	};

	public void onData(ByteBuffer bb) {
		ringBuffer.publishEvent(TRANSLATOR, bb);
	}
}
