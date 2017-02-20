package dahua.sheji.moshi.chapter01;

/**
 * @author win <br>
 *         简单工厂模式
 */
public class OperationTest {
	public static void main(String[] args) {
		Operation op = OperationFactory.create("+");
		op.setNumberA(1.2);
		op.setNumberB(2.3);
		double result = op.getResult();
		System.out.println(result);
	}
}
