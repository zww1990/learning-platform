package com.example.hibernate;

import java.lang.reflect.Method;
import javax.persistence.Column;
import javax.persistence.Table;
import org.junit.Test;

/**
 * @author zww
 */
public class AnnotationTest {
	@Test
	public void test1() {
		try {
			Table t = Object.class.getAnnotation(Table.class);
			Method[] methods = Object.class.getMethods();
			for (Method m : methods) {
				Column c = m.getAnnotation(Column.class);
				if (c != null) {
					System.err.println("SELECT COUNT(1) FROM " + t.name() + " WHERE " + c.name() + " IS NULL;");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
