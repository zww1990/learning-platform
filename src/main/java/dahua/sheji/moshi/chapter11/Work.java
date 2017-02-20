package dahua.sheji.moshi.chapter11;

public interface Work {

	void setHour(long hour);

	void setFinish(boolean finish);

	void setState(State state);

	void writeProgram();

	long getHour();

	boolean isFinish();
}
