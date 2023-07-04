package com.runoob.design.chapter4.javaee.pattern29;

import java.util.List;

/**
 * 学生DAO
 */
public interface StudentDao {
	List<Student> getAllStudents();

	Student getStudent(int rollNo);

	void updateStudent(Student student);

	void deleteStudent(Student student);
}
