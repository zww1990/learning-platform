package dahua.sheji.moshi.chapter07;

/**
 * @author win <br>
 *         模板方法模式
 */
public class PaperTest {
	public static void main(String[] args) {
		Paper p1 = new PaperA();
		p1.question1();
		p1.question2();
		p1.question3();

		Paper p2 = new PaperB();
		p2.question1();
		p2.question2();
		p2.question3();
	}
}
