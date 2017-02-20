package dahua.sheji.moshi.chapter09;

/**
 * @author win <br>
 *         观察者模式
 */
public class SubjectTest {
	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
		subject.attach(new ConcreteObserver("X", subject));
		subject.attach(new ConcreteObserver("Y", subject));
		subject.attach(new ConcreteObserver("Z", subject));
		subject.setSubjectState("ABC");
		subject.notice();
	}
}
