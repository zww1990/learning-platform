package dahua.sheji.moshi.chapter10.dao;

import dahua.sheji.moshi.chapter10.entity.Department;

public interface IDepartmentDao {
	void insert(Department department);

	Department getDepartment(Integer id);
}
