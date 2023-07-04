package com.runoob.design.chapter4.javaee.pattern33;

/**
 * 传输对象模式（Transfer Object Pattern）
 */
public class TransferObjectPatternDemo {
	/**
	 * 使用 StudentBO 来演示传输对象设计模式。
	 */
	public static void main(String[] args) {
		StudentBO studentBusinessObject = new StudentBO();

		// 输出所有的学生
		studentBusinessObject
				.getAllStudents()
				.stream()
				.forEach(
						student -> {
							System.out.println("Student: [RollNo : "
									+ student.getRollNo() + ", Name : "
									+ student.getName() + " ]");
						});
		// 更新学生
		StudentVO student = studentBusinessObject.getAllStudents().get(0);
		student.setName("Michael");
		studentBusinessObject.updateStudent(student);

		// 获取学生
		studentBusinessObject.getStudent(0);
		System.out.println("Student: [RollNo : " + student.getRollNo()
				+ ", Name : " + student.getName() + " ]");
	}
}
