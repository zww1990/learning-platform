package dahua.sheji.moshi.chapter14;

public class Abstraction {
	protected Implementor implementor;

	public void setImplementor(Implementor implementor) {
		this.implementor = implementor;
	}

	public void operation() {
		this.implementor.operation();
	}
}
