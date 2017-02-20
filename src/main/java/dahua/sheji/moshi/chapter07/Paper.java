package dahua.sheji.moshi.chapter07;

public abstract class Paper {
	public void question1() {
		System.out.println("question1");
		System.out.println("answer1=" + this.answer1());
		System.out.println();
	}

	public void question2() {
		System.out.println("question2");
		System.out.println("answer2=" + this.answer2());
		System.out.println();
	}

	public void question3() {
		System.out.println("question3");
		System.out.println("answer3=" + this.answer3());
		System.out.println();
	}

	public abstract String answer1();

	public abstract String answer2();

	public abstract String answer3();
}
