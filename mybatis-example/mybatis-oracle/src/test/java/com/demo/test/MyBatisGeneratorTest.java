package com.demo.test;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author ZhangWeiWei
 * @date 2018年9月30日,下午1:10:17
 * @description Java调用mybatis-generator类库反向生成POJO、DAO。
 */
public class MyBatisGeneratorTest {
	/**
	 * 主方法
	 */
	public static void main(String[] args) {
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
}
