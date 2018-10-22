package com.example.hibernate;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Environment.Variable;
import org.apache.tools.ant.types.Path;
import org.hibernate.tool.ant.Hbm2ControllerExporterTask;
import org.hibernate.tool.ant.Hbm2DAOExporterTask;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;
import org.hibernate.tool.ant.Hbm2ServiceExporterTask;
import org.hibernate.tool.ant.Hbm2ServiceImplExporterTask;
import org.hibernate.tool.ant.JDBCConfigurationTask;
import org.hibernate.tool.ant.MyHibernateToolTask;

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
			File destdir = new File(baseDir, "src\\main\\java");
			MyHibernateToolTask toolTask = new MyHibernateToolTask();
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
			javaExporterTask.setDestdir(destdir);
			Hbm2DAOExporterTask daoExporterTask = (Hbm2DAOExporterTask) toolTask.createHbm2DAO();
			// 设置生成DAO文件目录
			daoExporterTask.setDestdir(destdir);
			// 设置DAO的包名
			Variable property = new Variable();
			property.setKey("package-name");
			property.setValue("com.demo.repository");
			daoExporterTask.addConfiguredProperty(property);
			Hbm2ServiceExporterTask serviceExporterTask = (Hbm2ServiceExporterTask) toolTask.createHbm2Service();
			// 设置生成Service文件目录
			serviceExporterTask.setDestdir(destdir);
			// 设置Service的包名
			property = new Variable();
			property.setKey("package-name");
			property.setValue("com.demo.service");
			serviceExporterTask.addConfiguredProperty(property);
			Hbm2ServiceImplExporterTask serviceImplExporterTask = (Hbm2ServiceImplExporterTask) toolTask
					.createHbm2ServiceImpl(daoExporterTask, serviceExporterTask);
			// 设置生成Service Impl文件目录
			serviceImplExporterTask.setDestdir(destdir);
			// 设置Service Impl的包名
			property = new Variable();
			property.setKey("package-name");
			property.setValue("com.demo.service.impl");
			serviceImplExporterTask.addConfiguredProperty(property);
			Hbm2ControllerExporterTask controllerExporterTask = (Hbm2ControllerExporterTask) toolTask
					.createHbm2Controller(serviceExporterTask);
			// 设置生成Controller文件目录
			controllerExporterTask.setDestdir(destdir);
			// 设置Controller的包名
			property = new Variable();
			property.setKey("package-name");
			property.setValue("com.demo.controller");
			controllerExporterTask.addConfiguredProperty(property);
			toolTask.setProject(project);
			toolTask.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
