package dahua.sheji.moshi.all.creational.factory;

public class TeacherWorkFactory implements IWorkFactory {
	public Work getWork() {
		return new TeacherWork();
	}
}
