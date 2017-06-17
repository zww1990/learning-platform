package com.example.hibernate;

import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.cfilmcloud.collate.Application;
import com.cfilmcloud.collate.orm.domain.TSysTaskSchedule;
import com.cfilmcloud.collate.orm.repository.TSysTaskScheduleDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class JpaTest {
	@Resource
	private ApplicationContext context;

	@Test
	public void test1() {
		try {
			TSysTaskScheduleDao dao = this.context.getBean(TSysTaskScheduleDao.class);
			TSysTaskSchedule obj = new TSysTaskSchedule();
			obj.setTaskNo("1");
			obj.setWhat("2");
			obj.setIntervals("3");
			obj.setBdate("4");
			obj.setEdate("5");
			obj.setTimeCost(1);
			obj.setNoticeTel("6");
			obj.setNoticeType("7");
			obj.setIsSleeping(2);
			obj.setIsEnable(3);
			obj.setIsDelete(4);
			obj.setRemark("8");
			obj.setThisTime(new Date());
			obj.setNextTime(new Date());
			obj.setLastTime(new Date());
			obj.setRowId(17);
			dao.save(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2() {
		try {
			System.out.println(this.context.getBeanDefinitionCount());
			Arrays.stream(this.context.getBeanDefinitionNames()).forEach(x -> System.err.println(x));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test3() {
		try {
			TSysTaskScheduleDao dao = this.context.getBean(TSysTaskScheduleDao.class);
			TSysTaskSchedule obj = dao.findOne((root, query, builder) -> {
				return builder.equal(root.get("taskNo"), "1");
			});
			System.err.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
