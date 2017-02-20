package dahua.sheji.moshi.chapter05;

public class AddFactory implements IFactory {

	@Override
	public Operation create() {
		return new OperationAdd();
	}

}
