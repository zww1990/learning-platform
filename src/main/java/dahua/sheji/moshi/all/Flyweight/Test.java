package dahua.sheji.moshi.all.Flyweight;

/**
 * 运用共享技术有效地支持大量细粒度的对象。<br>
 * 适用性<br>
 * 当都具备下列情况时，使用Flyweight模式：<br>
 * 1.一个应用程序使用了大量的对象。<br>
 * 2.完全由于使用大量的对象，造成很大的存储开销。<br>
 * 3.对象的大多数状态都可变为外部状态。<br>
 * 4.如果删除对象的外部状态，那么可以相对较少的共享对象取代很多组对象。<br>
 * 5.应用程序不依赖于对象标识。由于Flyweight对象可以被共享，对于概念上明显有别的对象，标识测试将返回真值。<br>
 * 
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		Flyweight fly1 = FlyweightFactory.getFlyweight("a");
		fly1.action(1);

		Flyweight fly2 = FlyweightFactory.getFlyweight("a");
		System.out.println(fly1 == fly2);

		Flyweight fly3 = FlyweightFactory.getFlyweight("b");
		fly3.action(2);

		Flyweight fly4 = FlyweightFactory.getFlyweight("c");
		fly4.action(3);

		Flyweight fly5 = FlyweightFactory.getFlyweight("d");
		fly5.action(4);

		System.out.println(FlyweightFactory.getSize());
	}
}
