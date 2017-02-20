package dahua.sheji.moshi.chapter03;

public class ConcreteDecoratorA extends Decorator {
	private String addedState;

	@Override
	public void operation() {
		super.operation();
		this.addedState = "New State";
		System.out
				.println("void dahua.sheji.moshi.chapter3.ConcreteDecoratorA.operation()");
	}
}
