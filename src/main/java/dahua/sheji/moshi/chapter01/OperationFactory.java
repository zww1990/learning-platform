package dahua.sheji.moshi.chapter01;

public class OperationFactory {
	public static Operation create(String operate) {
		switch (operate) {
		case "+":
			return new OperationAdd();
		case "-":
			return new OperationSub();
		case "*":
			return new OperationMul();
		case "/":
			return new OperationDiv();
		default:
			return null;
		}
	}
}
