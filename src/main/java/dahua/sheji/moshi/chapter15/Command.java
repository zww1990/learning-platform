package dahua.sheji.moshi.chapter15;

public abstract class Command {
	protected Receiver receiver;

	public Command(Receiver receiver) {
		this.receiver = receiver;
	}

	public abstract void execute();
}
