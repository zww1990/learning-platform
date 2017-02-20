package dahua.sheji.moshi.chapter02;

/**
 * @author win <br>
 *         策略模式
 */
public class CashTest {
	public static void main(String[] args) {
		// CashSuper ca = CashFactory.create("打8折");
		// double result = ca.acceptCash(100d);
		// System.out.println(result);
		CashContext ca = new CashContext("打8折");
		double result = ca.getResult(100d);
		System.out.println(result);
	}
}
