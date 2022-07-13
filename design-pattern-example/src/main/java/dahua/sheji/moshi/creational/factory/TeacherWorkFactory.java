package dahua.sheji.moshi.creational.factory;

public class TeacherWorkFactory implements IWorkFactory {
	public Work getWork() {
		return new TeacherWork();
	}
}
