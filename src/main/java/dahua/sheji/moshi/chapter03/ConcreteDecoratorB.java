package dahua.sheji.moshi.chapter03;

public class ConcreteDecoratorB extends Decorator {
	@Override
	public void operation() {
		super.operation();
		this.addedBehavior();
		System.out
				.println("void dahua.sheji.moshi.chapter3.ConcreteDecoratorB.operation()");
	}

	private void addedBehavior() {
		System.out.println("addedBehavior()");
	}
}
