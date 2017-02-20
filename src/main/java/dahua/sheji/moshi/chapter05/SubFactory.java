package dahua.sheji.moshi.chapter05;

public class SubFactory implements IFactory {

	@Override
	public Operation create() {
		return new OperationSub();
	}

}
