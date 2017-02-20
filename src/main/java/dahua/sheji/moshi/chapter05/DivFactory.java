package dahua.sheji.moshi.chapter05;

public class DivFactory implements IFactory {

	@Override
	public Operation create() {
		return new OperationDiv();
	}

}
