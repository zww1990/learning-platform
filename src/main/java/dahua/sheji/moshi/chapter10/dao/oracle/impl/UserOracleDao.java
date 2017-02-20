package dahua.sheji.moshi.chapter10.dao.oracle.impl;

import dahua.sheji.moshi.chapter10.dao.IUserDao;
import dahua.sheji.moshi.chapter10.entity.User;

public class UserOracleDao implements IUserDao {

	@Override
	public void insert(User user) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.oracle.impl.UserOracleDao.insert(User)");
	}

	@Override
	public User getUser(Integer id) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.oracle.impl.UserOracleDao.getUser(Integer)");
		return null;
	}

}
