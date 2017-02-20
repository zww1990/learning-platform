package dahua.sheji.moshi.all.Factory;

public class TeacherWorkFactory implements IWorkFactory {
	public Work getWork() {
		return new TeacherWork();
	}
}
