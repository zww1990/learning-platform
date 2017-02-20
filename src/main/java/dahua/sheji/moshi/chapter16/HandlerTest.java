package dahua.sheji.moshi.chapter16;

/**
 * @author win <br>
 *         职责链模式
 */
public class HandlerTest {
	public static void main(String[] args) {
		Handler h1 = new ConcreteHandler1();
		Handler h2 = new ConcreteHandler2();
		Handler h3 = new ConcreteHandler3();
		h1.setSuccessor(h2);
		h2.setSuccessor(h3);
		int[] requests = { 2, 5, 14, 22, 18, 3, 27, 20 };
		for (int i : requests) {
			h1.handleRequest(i);
		}
	}
}
