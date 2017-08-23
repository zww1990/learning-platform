package net.example.chapter025;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {
	/**
	 * 并发任务间的数据交换
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> buffer1 = new ArrayList<String>();
		List<String> buffer2 = new ArrayList<String>();
		Exchanger<List<String>> exchanger = new Exchanger<List<String>>();
		Producer producer = new Producer(buffer1, exchanger);
		Consumer consumer = new Consumer(buffer2, exchanger);
		Thread tp = new Thread(producer);
		Thread tc = new Thread(consumer);
		tp.start();
		tc.start();
	}
}
