package dahua.sheji.moshi.chapter06;

/**
 * @author win <br>
 *         浅复制与深复制
 */
public class CloneTest {
	public static void main(String[] args) {
		Professor p = new Professor("aa", 11);
		Student s1 = new Student("zhangsan", 12, p);
		Student s2 = (Student) s1.clone();
		s2.setAge(20);
		s2.setName("lisi");
		s2.getP().setAge(22);
		s2.getP().setName("bb");
		System.out.println(s1);
		System.out.println(s2);
	}
}
