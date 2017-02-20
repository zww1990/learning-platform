package dahua.sheji.moshi.chapter13;

/**
 * @author win <br>
 *         单例模式
 */
public class SingletonTest {
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		if (s1 == s2) {
			System.out.println("两个对象是相同的实例");
		}
	}
}
