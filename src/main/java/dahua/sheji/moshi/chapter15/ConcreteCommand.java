package dahua.sheji.moshi.chapter15;

public class ConcreteCommand extends Command {

	public ConcreteCommand(Receiver receiver) {
		super(receiver);
	}

	@Override
	public void execute() {
		super.receiver.action();
	}

}
