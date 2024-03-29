package com.runoob.design.chapter1.creational.pattern03;

/**
 * 6、枚举<br>
 * JDK 版本：JDK1.5 起<br>
 * 是否 Lazy 初始化：否<br>
 * 是否多线程安全：是<br>
 * 实现难度：易<br>
 * 描述：这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。<br>
 * 它更简洁，自动支持序列化机制，绝对防止多次实例化。<br>
 * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，<br>
 * 它不仅能避免多线程同步问题，而且还自动支持序列化机制，<br>
 * 防止反序列化重新创建新的对象，绝对防止多次实例化。<br>
 * 不过，由于 JDK1.5 之后才加入 enum 特性，<br>
 * 用这种方式写不免让人感觉生疏，在实际工作中，也很少用。<br>
 * 不能通过 reflection attack 来调用私有构造方法。<br>
 */
public enum Singleton6 {
	INSTANCE;
}
