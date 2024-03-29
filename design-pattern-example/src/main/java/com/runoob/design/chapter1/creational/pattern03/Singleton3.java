package com.runoob.design.chapter1.creational.pattern03;

/**
 * 3、饿汉式<br>
 * 是否 Lazy 初始化：否<br>
 * 是否多线程安全：是<br>
 * 实现难度：易<br>
 * 描述：这种方式比较常用，但容易产生垃圾对象。<br>
 * 优点：没有加锁，执行效率会提高。<br>
 * 缺点：类加载时就初始化，浪费内存。<br>
 * 它基于 classloader 机制避免了多线程的同步问题，不过，instance 在类装载时就实例化，<br>
 * 虽然导致类装载的原因有很多种，在单例模式中大多数都是调用 getInstance 方法， <br>
 * 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，<br>
 * 这时候初始化 instance 显然没有达到 lazy loading 的效果。<br>
 */
public class Singleton3 {
	private static Singleton3 instance = new Singleton3();

	private Singleton3() {
	}

	public static Singleton3 getInstance() {
		return instance;
	}
}
