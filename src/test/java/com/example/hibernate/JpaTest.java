package com.example.hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import com.demo.Application;
import com.demo.collate.orm.domain.TSysDsConfigItem;
import com.demo.collate.orm.domain.TSysTaskCheckResult;
import com.demo.collate.orm.domain.TSysTaskCheckResultCommon;
import com.demo.collate.orm.domain.TSysTaskConfig;
import com.demo.collate.orm.domain.TSysTaskSchedule;
import com.demo.collate.orm.repository.TSysDsConfigItemDao;
import com.demo.collate.orm.repository.TSysTaskCheckResultCommonDao;
import com.demo.collate.orm.repository.TSysTaskCheckResultDao;
import com.demo.collate.orm.repository.TSysTaskConfigDao;
import com.demo.collate.orm.repository.TSysTaskScheduleDao;

@RunWith(SpringRunner.class)
@SuppressWarnings("unchecked")
@SpringBootTest(classes = Application.class)
public class JpaTest {
	@Resource
	private ApplicationContext context;
	@PersistenceContext
	private EntityManager em;
	@Resource
	private JdbcTemplate jt;

	@Test
	@Transactional
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
			// obj.setRowId(17);
			// dao.save(obj);
			System.err.println(dao.deleteByRowIdAndTaskNo(Arrays.asList(17, 18), "11"));
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
			System.err.println(dao.count((root, query, builder) -> builder.notEqual(root.get("taskNo"), "1")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4() {
		try {
			CriteriaBuilder builder = this.em.getCriteriaBuilder();
			CriteriaQuery<TSysTaskSchedule> query = builder.createQuery(TSysTaskSchedule.class);
			Root<TSysTaskSchedule> root = query.from(TSysTaskSchedule.class);
			query.where(builder.equal(root.get("taskNo"), "1"));
			List<TSysTaskSchedule> list = this.em.createQuery(query).getResultList();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test5() {
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select * from t_sys_task_check_result_");
			sb.append("t1000019");
			sb.append(" where 1=1 ");
			List<TSysTaskCheckResultCommon> list = this.em
					.createNativeQuery(sb.toString(), TSysTaskCheckResultCommon.class).getResultList();
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	public void test6() {
		try {
			TSysTaskCheckResultCommonDao dao = this.context.getBean(TSysTaskCheckResultCommonDao.class);
			String table = "t1000019";
			List<TSysTaskCheckResultCommon> list = dao.select(table);
			System.out.println(list.size());
			System.out.println(dao.count(table));
			dao.dropTable("t100001820170613135553581");
			String result = dao.taskExecCreateTable("T1000018", "T100001820170609184737790", "2017-06-08至2017-06-08");
			System.out.println(result);
			System.out.println(dao.taskCheckResultCreateTable("123456"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test7() {
		try {
			TSysTaskCheckResultDao dao = this.context.getBean(TSysTaskCheckResultDao.class);
			int pageNum = 1;
			int pageSize = 2;
			pageNum = pageNum < 0 ? 0 : pageNum;
			pageSize = pageSize < 1 ? 1 : pageSize;
			Pageable pageable = new PageRequest(pageNum, pageSize);
			Page<TSysTaskCheckResult> page = dao.findAll((root, query, builder) -> {
				List<Predicate> list = new ArrayList<>();
				list.add(builder.equal(root.get("checkResult"), 2));
				list.add(builder.between(root.get("checkDateBegin"), new Date(), new Date()));
				list.add(builder.between(root.get("checkDateEnd"), new Date(), new Date()));
				list.add(root.get("checkResult").in(1, 2).not());
				Subquery<String> sq = query.subquery(String.class);
				Root<TSysTaskConfig> sr = sq.from(TSysTaskConfig.class);
				sq.select(sr.get("taskNo"));
				sq.where(builder.like(builder.upper(sr.get("taskDesc")), "%" + "a".toUpperCase() + "%"));
				list.add(root.get("taskNo").in(sq));
				if (!list.isEmpty()) {
					query.where(list.toArray(new Predicate[list.size()]));
				}
				query.orderBy(builder.desc(root.get("rowId")));
				return query.getRestriction();
			}, pageable);
			System.err.println("getNumber=" + page.getNumber());
			System.err.println("getNumberOfElements=" + page.getNumberOfElements());
			System.err.println("getSize=" + page.getSize());
			System.err.println("getTotalElements=" + (int) page.getTotalElements());
			System.err.println("getTotalPages=" + page.getTotalPages());
			page.getContent().stream().forEach(x -> System.err.println(x.getRowId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test8() {
		try {
			TSysTaskConfigDao dao = this.context.getBean(TSysTaskConfigDao.class);
			TSysTaskConfig obj = dao.findByTaskNo("T1000019");
			System.out.println(obj);
			System.err.println(dao.generateTaskNo());
			System.err.println(dao.findTaskNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test9() {
		try {
			TSysDsConfigItemDao dao = this.context.getBean(TSysDsConfigItemDao.class);
			List<TSysDsConfigItem> list1 = dao.findADsConfigItem("DS000010");
			System.out.println(list1.size());
			List<TSysDsConfigItem> list2 = dao.findBDsConfigItem("DS000005");
			System.out.println(list2.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test10() {
		try {
			String sql = "INSERT INTO t_sys_task_schedule (task_no) VALUES ('a'),('b')";
			int rows = this.jt.update(sql);
			System.err.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
