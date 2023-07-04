package com.runoob.design.chapter4.javaee.pattern26;

/**
 * 学生视图
 */
public class StudentView {
	public void printStudentDetails(String studentName, String studentRollNo) {
		System.out.println("Student: ");
		System.out.println("Name: " + studentName);
		System.out.println("Roll No: " + studentRollNo);
	}
}
