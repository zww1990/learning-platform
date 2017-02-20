package dahua.sheji.moshi.chapter01;

public class OperationSub extends Operation {

	@Override
	public double getResult() {
		return this.getNumberA() - this.getNumberB();
	}

}
