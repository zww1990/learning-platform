package dahua.sheji.moshi.chapter05;

public class OperationDiv extends Operation {

	@Override
	public double getResult() {
		if (this.getNumberB() == 0) {
			throw new RuntimeException("除数不能为零。");
		}
		return this.getNumberA() / this.getNumberB();
	}

}
