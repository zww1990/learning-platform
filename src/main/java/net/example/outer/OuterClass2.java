package net.example.outer;

public class OuterClass2 {
	public InnerClass2 getInnerClass(final int num, String str2) {
		return new InnerClass2() {
			int number = num + 3;

			public int getNumber() {
				return number;
			}
		}; /* 注意：分号不能省 */
	}

	public static void main(String[] args) {
		OuterClass2 out = new OuterClass2();
		InnerClass2 inner = out.getInnerClass(2, "chenssy");
		System.out.println(inner.getNumber());
	}
}

interface InnerClass2 {
	int getNumber();
}
