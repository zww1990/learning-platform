package net.other.outer;

public class OuterClass {
	private String name;
	private int age;
	private String str;

	public class InnerClass {
		public InnerClass() {
			name = "chenssy";
			age = 23;
		}

		public void display() {
			System.out.println("name：" + name + "   age：" + age);
		}

		public OuterClass getOuterClass() {
			return OuterClass.this;
		}

		public void innerDisplay() {
			// 使用外围内的属性
			str = "chenssy...";
			System.out.println(str);
			// 使用外围内的方法
			outerDisplay();
		}
	}

	public void display() {
		System.out.println("OuterClass...");
	}

	public void outerDisplay() {
		System.out.println("outerClass...");
	}

	public InnerClass getInnerClass() {
		return new InnerClass();
	}

	public static void main(String[] args) {
		OuterClass outerClass = new OuterClass();
		// OuterClass.InnerClass innerClass = outerClass.new InnerClass();
		// innerClass.display();
		// innerClass.getOuterClass().display();
		OuterClass.InnerClass innerClass = outerClass.getInnerClass();
		innerClass.innerDisplay();
	}
}
