package dahua.sheji.moshi.chapter10;

import dahua.sheji.moshi.chapter10.dao.IDepartmentDao;
import dahua.sheji.moshi.chapter10.dao.IUserDao;

/**
 * @author win <br>
 *         反射+抽象工厂模式
 */
public class DaoTest {
	public static void main(String[] args) throws Exception {
		IUserDao userDao = DataAccess.getBean(IUserDao.class);
		IDepartmentDao departDao = DataAccess.getBean(IDepartmentDao.class);
		userDao.getUser(null);
		userDao.insert(null);
		departDao.getDepartment(null);
		departDao.insert(null);
	}
}
