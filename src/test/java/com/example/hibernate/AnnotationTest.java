package com.example.hibernate;

import java.lang.reflect.Method;

import javax.persistence.Column;
import javax.persistence.Table;

import org.junit.Test;

import com.cfilmcloud.poly.orm.domain.TDmDzTicketOrder;
import com.cfilmcloud.poly.orm.domain.TDmDzTicketOrderId;

/**
 * @author zww
 *
 */
public class AnnotationTest {
	@Test
	public void test1() {
		try {
			Table t = TDmDzTicketOrder.class.getAnnotation(Table.class);
			Method[] methods = TDmDzTicketOrderId.class.getMethods();
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
