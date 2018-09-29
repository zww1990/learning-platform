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
 * @description java调用hibernate-tools类库反向生成pojo。
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
			configurationTask.setPackageName("com.demo.domain");
			configurationTask.setConfigurationFile(new File(baseDir, "src\\main\\resources\\hibernate.cfg.xml"));
			configurationTask.setRevEngFile(new Path(project, "src\\main\\resources\\hibernate.reveng.xml"));
			Hbm2JavaExporterTask javaExporterTask = (Hbm2JavaExporterTask) toolTask.createHbm2Java();
			javaExporterTask.setEjb3(true);
			javaExporterTask.setJdk5(true);
			javaExporterTask.setDestdir(new File(baseDir, "src\\main\\java"));
			Hbm2DAOExporterTask daoExporterTask = (Hbm2DAOExporterTask) toolTask.createHbm2DAO();
			daoExporterTask.setDestdir(new File(baseDir, "src\\main\\java"));
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
