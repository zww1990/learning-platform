package dahua.sheji.moshi.chapter10;

import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;

@SuppressWarnings("unchecked")
public class DataAccess {
	private static final Properties props = new Properties();
	static {
		try {
			props.load(DataAccess.class.getResourceAsStream("conf.properties"));
			System.out.println("正在读取资源属性文件中......");
			System.out.println(props);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T getBean(Class<T> type) {
		try {
			Class<?> obj = Class
					.forName(props.getProperty(type.getSimpleName()));
			return (T) obj.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		try {
			props.put("IDepartmentDao",
					"dahua.sheji.moshi.chapter10.dao.mysql.impl.DepartmentMysqlDao");
			props.put("IUserDao",
					"dahua.sheji.moshi.chapter10.dao.oracle.impl.UserOracleDao");
			Writer writer = new FileWriter(
					"src/main/java/dahua/sheji/moshi/chapter10/conf.properties");
			props.store(writer, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
