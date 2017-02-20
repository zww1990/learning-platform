package dahua.sheji.moshi.chapter15;

/**
 * @author win <br>
 *         命令模式
 */
public class CommandTest {
	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		Command command = new ConcreteCommand(receiver);
		Invoker invoker = new Invoker();
		invoker.setCommand(command);
		invoker.executeCommand();
	}
}
