package dahua.sheji.moshi.creational.builder;

/**
 * 将一个复杂对象的构建与它的表示分离，使同样的构建过程可以创建不同的表示。<br>
 * 适用性<br>
 * 1.当创建复杂对象的算法应该 独立于该对象的组成部分 以及它们的装配方式时。<br>
 * 2.当构造过程必须允许被构造的对象有不同的表示时。<br>
 * 
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		PersonDirector pd = new PersonDirector();
		Person person = pd.constructPerson(new ManBuilder());
		System.out.println(person.getBody());
		System.out.println(person.getFoot());
		System.out.println(person.getHead());
	}
}
