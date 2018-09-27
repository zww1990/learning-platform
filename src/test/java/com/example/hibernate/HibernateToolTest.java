package com.example.hibernate;

import java.io.File;
import org.apache.tools.ant.Project;
import org.hibernate.tool.ant.AnnotationConfigurationTask;
import org.hibernate.tool.ant.Hbm2JavaExporterTask;
import org.hibernate.tool.ant.HibernateToolTask;

public class HibernateToolTest {
	public static void main(String[] args) {
		try {
			HibernateToolTask hibernateToolTask = new HibernateToolTask();
			AnnotationConfigurationTask annotationConfigurationTask = hibernateToolTask.createAnnotationConfiguration();
			annotationConfigurationTask.setConfigurationFile(
					new File("D:\\Projects\\zww\\hibernate5\\src\\main\\resources\\hibernate.cfg.xml"));
			Hbm2JavaExporterTask hbm2JavaExporterTask = (Hbm2JavaExporterTask) hibernateToolTask.createHbm2Java();
			hbm2JavaExporterTask.setEjb3(true);
			hbm2JavaExporterTask.setJdk5(true);
			hbm2JavaExporterTask.setDestdir(new File("D:\\zzzzzzzzzz\\"));
			Project project = new Project();
			project.setBaseDir(new File("D:\\zzzzzzzzzz\\"));
			hibernateToolTask.setProject(project);
			hibernateToolTask.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
