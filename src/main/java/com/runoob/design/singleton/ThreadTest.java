package com.runoob.design.singleton;

/**
 * @author Alienware <br>
 *         多线程测试单例模式
 */
public class ThreadTest {
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(() -> {
				System.out.println(Singleton5.getInstance());
			});
			t.start();
		}
	}
}
