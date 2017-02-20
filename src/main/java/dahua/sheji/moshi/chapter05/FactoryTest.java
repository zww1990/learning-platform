package dahua.sheji.moshi.chapter05;

/**
 * @author win <br>
 *         工厂方法模式
 */
public class FactoryTest {
	public static void main(String[] args) {
		IFactory factory = new AddFactory();
		Operation op = factory.create();
		op.setNumberA(11.2);
		op.setNumberB(22.3);
		double result = op.getResult();
		System.out.println(result);
	}
}
