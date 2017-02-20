package dahua.sheji.moshi.chapter10.dao;

import dahua.sheji.moshi.chapter10.entity.User;

public interface IUserDao {
	void insert(User user);

	User getUser(Integer id);
}
