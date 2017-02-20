package dahua.sheji.moshi.chapter11;

public class SleepingState implements State {

	@Override
	public void writeProgram(Work work) {
		System.out.println("当前时间：" + work.getHour() + "点，不行了，睡着了。");
	}

}
