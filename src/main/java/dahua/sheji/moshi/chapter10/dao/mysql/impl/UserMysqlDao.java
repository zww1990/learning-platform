package dahua.sheji.moshi.chapter10.dao.mysql.impl;

import dahua.sheji.moshi.chapter10.dao.IUserDao;
import dahua.sheji.moshi.chapter10.entity.User;

public class UserMysqlDao implements IUserDao {

	@Override
	public void insert(User user) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.mysql.impl.UserMysqlDao.insert(User)");
	}

	@Override
	public User getUser(Integer id) {
		System.out.println("dahua.sheji.moshi.chapter10.dao.mysql.impl.UserMysqlDao.getUser(Integer)");
		return null;
	}

}
