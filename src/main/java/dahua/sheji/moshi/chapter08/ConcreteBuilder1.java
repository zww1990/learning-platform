package dahua.sheji.moshi.chapter08;

public class ConcreteBuilder1 extends Builder {
	private Product product = new Product();

	@Override
	public void buildPartA() {
		this.product.add("部件A");
	}

	@Override
	public void buildPartB() {
		this.product.add("部件B");
	}

	@Override
	public Product getResult() {
		return this.product;
	}

}
