package com.example.hibernate;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.domain.Commit;
import com.demo.repository.CommitDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class CommitDaoTest {
	@Resource
	private CommitDao commitDao;

	@Test
	public void testSave() {
		try {
			Commit commit = new Commit();
			commit.setDeleted(true);
			commit.setComment("comment");
			commit.setChangesets("changesets");
			System.err.println(this.commitDao.save(commit));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQuery() {
		try {
			this.commitDao.findAll().stream().forEach(x -> {
				System.err.println(x.getChangesets() + "\t" + x.isDeleted());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
