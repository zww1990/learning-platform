package com.example.hibernate;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Environment.Variable;
import org.apache.tools.ant.types.Path;
import org.hibernate.tool.ant.Hbm2DAOExporterTask;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;
import org.hibernate.tool.ant.HibernateToolTask;
import org.hibernate.tool.ant.JDBCConfigurationTask;

/**
 * @author ZhangWeiWei
 * @date 2018年9月28日,下午2:24:19
 * @description java调用hibernate-tools类库反向生成POJO、JPA DAO。
 */
public class HibernateToolTest {
	/**
	 * 主方法
	 */
	public static void main(String[] args) {
		try {
			Project project = new Project();
			File baseDir = project.getBaseDir();
			HibernateToolTask toolTask = new HibernateToolTask();
			JDBCConfigurationTask configurationTask = toolTask.createJDBCConfiguration();
			// 设置POJO的包名
			configurationTask.setPackageName("com.demo.domain");
			// 设置hibernate的配置文件
			configurationTask.setConfigurationFile(new File(baseDir, "src\\main\\resources\\hibernate.cfg.xml"));
			// 设置hibernate的反向工程配置文件
			configurationTask.setRevEngFile(new Path(project, "src\\main\\resources\\hibernate.reveng.xml"));
			Hbm2JavaExporterTask javaExporterTask = (Hbm2JavaExporterTask) toolTask.createHbm2Java();
			// 设置生成EJB 3注解
			javaExporterTask.setEjb3(true);
			// 设置生成JDK 5语法，例如泛型和静态导入
			javaExporterTask.setJdk5(true);
			// 设置生成POJO文件目录
			javaExporterTask.setDestdir(new File(baseDir, "src\\main\\java"));
			Hbm2DAOExporterTask daoExporterTask = (Hbm2DAOExporterTask) toolTask.createHbm2DAO();
			// 设置生成DAO文件目录
			daoExporterTask.setDestdir(new File(baseDir, "src\\main\\java"));
			// 设置DAO的包名
			Variable property = new Variable();
			property.setKey("package-name");
			property.setValue("com.demo.repository");
			daoExporterTask.addConfiguredProperty(property);
			toolTask.setProject(project);
			toolTask.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
