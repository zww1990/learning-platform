package dahua.sheji.moshi.chapter03;

/**
 * @author win <br>
 *         装饰模式
 */
public class DecoratorTest {
	public static void main(String[] args) {
		ConcreteComponent cc = new ConcreteComponent();
		ConcreteDecoratorA cda = new ConcreteDecoratorA();
		ConcreteDecoratorB cdb = new ConcreteDecoratorB();
		cda.setComponent(cc);
		cdb.setComponent(cda);
		cdb.operation();
	}
}
