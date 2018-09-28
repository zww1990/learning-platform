package com.example.hibernate;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;
import org.hibernate.tool.ant.HibernateToolTask;
import org.hibernate.tool.ant.JDBCConfigurationTask;

public class HibernateToolTest {
	public static void main(String[] args) {
		try {
			Project project = new Project();
			File baseDir = project.getBaseDir();
			HibernateToolTask toolTask = new HibernateToolTask();
			JDBCConfigurationTask configurationTask = toolTask.createJDBCConfiguration();
			configurationTask.setPackageName("com.demo.domain");
			configurationTask.setConfigurationFile(new File(baseDir, "src\\main\\resources\\hibernate.cfg.xml"));
			configurationTask.setRevEngFile(new Path(project, "src\\main\\resources\\hibernate.reveng.xml"));
			Hbm2JavaExporterTask exporterTask = (Hbm2JavaExporterTask) toolTask.createHbm2Java();
			exporterTask.setEjb3(true);
			exporterTask.setJdk5(true);
			exporterTask.setDestdir(new File(baseDir, "src\\main\\java"));
			toolTask.setProject(project);
			toolTask.execute();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
