package dahua.sheji.moshi.chapter11;

/**
 * @author win <br>
 *         状态模式
 */
public class WorkTest {
	public static void main(String[] args) {
		Work work = new WorkImpl();
		work.setHour(9);
		work.writeProgram();
		work.setHour(12);
		work.writeProgram();
		work.setHour(13);
		work.writeProgram();
		work.setHour(17);
		work.writeProgram();
		work.setHour(19);
		work.setFinish(true);
		work.writeProgram();
		work.setHour(22);
		work.writeProgram();
	}
}
