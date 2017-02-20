package dahua.sheji.moshi.chapter11;

public class WorkImpl implements Work {
	private State state;
	private long hour;
	private boolean finish;

	public WorkImpl() {
		this.state = new ForenoonState();
	}

	@Override
	public void writeProgram() {
		this.state.writeProgram(this);
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public long getHour() {
		return hour;
	}

	@Override
	public void setHour(long hour) {
		this.hour = hour;
	}

	@Override
	public boolean isFinish() {
		return finish;
	}

	@Override
	public void setFinish(boolean finish) {
		this.finish = finish;
	}

}
