package com.demo.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.MyDefaultCommentGenerator.ExampleFields;
import org.mybatis.generator.internal.MyDefaultCommentGenerator.MethodComments;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MybatisTest {
	@Test
	public void generate() {
		try {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			Resource resource = new ClassPathResource("generatorConfig.xml");
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(resource.getInputStream());
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
			System.out.println("自动生成完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void mysql() {
		String url = "jdbc:mysql://localhost:3306/test";
		Properties info = new Properties();
		info.put("user", "root");
		info.put("password", "root");
		info.put("useInformationSchema", "true");
		try (Connection conn = DriverManager.getConnection(url, info)) {
			DatabaseMetaData metadata = conn.getMetaData();
			ResultSet tables = metadata.getTables(null, null, "user", null);
			while (tables.next()) {
				String remarks = tables.getString("REMARKS"); //$NON-NLS-1$
				System.err.println(remarks);
			}
			ResultSet columns = metadata.getColumns(null, null, "user", null);
			while (columns.next()) {
				String remarks = columns.getString("REMARKS"); //$NON-NLS-1$
				System.err.println(remarks);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMethodComments() {
		try {
			System.err.println(MethodComments.value("aa"));
			System.err.println(MethodComments.value(MethodComments.deleteByPrimaryKey.name()));
			System.err.println(ExampleFields.hasField("bb"));
			System.err.println(ExampleFields.hasField(ExampleFields.distinct.name()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
