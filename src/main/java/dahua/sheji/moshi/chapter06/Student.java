package dahua.sheji.moshi.chapter06;

public class Student implements Cloneable {
	private String name;
	private int age;
	private Professor p;

	public Student(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Student(String name, int age, Professor p) {
		this.name = name;
		this.age = age;
		this.p = p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Professor getP() {
		return p;
	}

	public void setP(Professor p) {
		this.p = p;
	}

	public Object clone() {
		Student o = null;
		try {
			o = (Student) super.clone();
			o.p = (Professor) this.p.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public String toString() {
		return String.format("Student [name=%s, age=%s, p=%s]", name, age, p);
	}
}
