package dahua.sheji.moshi.chapter09;

public class ConcreteObserver extends Observer {
	private String name;
	private String observerState;
	private ConcreteSubject subject;

	public ConcreteObserver(String name, ConcreteSubject subject) {
		this.name = name;
		this.subject = subject;
	}

	@Override
	public void update() {
		this.observerState = this.subject.getSubjectState();
		System.out.println("观察者" + this.name + "的新状态是" + this.observerState);
	}

	public ConcreteSubject getSubject() {
		return subject;
	}

	public void setSubject(ConcreteSubject subject) {
		this.subject = subject;
	}

}
