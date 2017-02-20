package dahua.sheji.moshi.chapter08;

public class ConcreteBuilder2 extends Builder {
	private Product product = new Product();

	@Override
	public void buildPartA() {
		this.product.add("部件X");
	}

	@Override
	public void buildPartB() {
		this.product.add("部件Y");
	}

	@Override
	public Product getResult() {
		return this.product;
	}

}
