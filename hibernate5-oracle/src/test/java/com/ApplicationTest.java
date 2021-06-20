package com;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.querydsl.jpa.impl.JPAQueryFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	@Resource
	private JPAQueryFactory queryFactory;

	@Test
	public void select() {
		try {
			System.err.println(this.queryFactory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
