package com.example.hibernate;

import java.lang.reflect.Method;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.cfilmcloud.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AnnotationTest {
	@PersistenceContext
	private EntityManager em;

	@Test
	public void test1() {
		try {
			Table t = Object.class.getAnnotation(Table.class);
			Method[] methods = Object.class.getMethods();
			for (Method m : methods) {
				Column c = m.getAnnotation(Column.class);
				if (c != null) {
					String sql = "SELECT COUNT(1) FROM " + t.name() + " WHERE " + c.name() + " IS NULL;";
					Number res = (Number) this.em.createNativeQuery(sql).getSingleResult();
					int count = res.intValue();
					if (count > 0) {
						System.err.println(m.getName());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
