package dahua.sheji.moshi.chapter05;

public class OperationMul extends Operation {

	@Override
	public double getResult() {
		return this.getNumberA() * this.getNumberB();
	}

}
