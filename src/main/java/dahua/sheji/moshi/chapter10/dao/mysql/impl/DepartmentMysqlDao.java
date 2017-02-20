package dahua.sheji.moshi.chapter10.dao.mysql.impl;

import dahua.sheji.moshi.chapter10.dao.IDepartmentDao;
import dahua.sheji.moshi.chapter10.entity.Department;

public class DepartmentMysqlDao implements IDepartmentDao {

	@Override
	public void insert(Department department) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.mysql.impl.DepartmentMysqlDao.insert(Department)");
	}

	@Override
	public Department getDepartment(Integer id) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.mysql.impl.DepartmentMysqlDao.getDepartment(Integer)");
		return null;
	}

}
