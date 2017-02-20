package dahua.sheji.moshi.chapter15;

public class Invoker {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void executeCommand() {
		this.command.execute();
	}
}
