package dahua.sheji.moshi.chapter14;

/**
 * @author win <br>
 *         桥接模式
 */
public class AbstractionTest {
	public static void main(String[] args) {
		Abstraction ab = new Abstraction();
		ab.setImplementor(new ConcreteImplementorA());
		ab.operation();
		ab.setImplementor(new ConcreteImplementorB());
		ab.operation();
	}
}
