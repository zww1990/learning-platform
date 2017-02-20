package dahua.sheji.moshi.chapter05;

public class MulFactory implements IFactory {

	@Override
	public Operation create() {
		return new OperationMul();
	}

}
