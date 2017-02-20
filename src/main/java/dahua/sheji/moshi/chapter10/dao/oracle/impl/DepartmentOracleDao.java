package dahua.sheji.moshi.chapter10.dao.oracle.impl;

import dahua.sheji.moshi.chapter10.dao.IDepartmentDao;
import dahua.sheji.moshi.chapter10.entity.Department;

public class DepartmentOracleDao implements IDepartmentDao {

	@Override
	public void insert(Department department) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.oracle.impl.DepartmentOracleDao.insert(Department)");
	}

	@Override
	public Department getDepartment(Integer id) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.oracle.impl.DepartmentOracleDao.getDepartment(Integer)");
		return null;
	}

}
