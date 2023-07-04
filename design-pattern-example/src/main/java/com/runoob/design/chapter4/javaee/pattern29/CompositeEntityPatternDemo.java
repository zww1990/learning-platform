package com.runoob.design.chapter4.javaee.pattern29;

/**
 * 数据访问对象模式（Data Access Object Pattern）
 */
public class CompositeEntityPatternDemo {
	/**
	 * 使用 StudentDao 来演示数据访问对象模式的用法。
	 */
	public static void main(String[] args) {
		StudentDao studentDao = new StudentDaoImpl();

		// 输出所有的学生
		studentDao.getAllStudents().forEach(student -> {
							System.out.println("Student: [RollNo : "
									+ student.getRollNo() + ", Name : "
									+ student.getName() + " ]");
						});

		// 更新学生
		Student student = studentDao.getAllStudents().get(0);
		student.setName("Michael");
		studentDao.updateStudent(student);

		// 获取学生
		studentDao.getStudent(0);
		System.out.println("Student: [RollNo : " + student.getRollNo()
				+ ", Name : " + student.getName() + " ]");
	}
}
