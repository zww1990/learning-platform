package com.demo.test;

import java.util.Collections;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class FastAutoGeneratorTests {
	String url = "jdbc:mysql://localhost:3306/example?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai";
	String username = "root";
	String password = "root";

	@Test
	public void testGenerator() {
		try {
			String userDir = System.getProperty("user.dir");
			FastAutoGenerator.create(url, username, password)// 数据库配置
					.globalConfig(// 全局配置
							builder -> builder.author("javaer") // 作者名
									.dateType(DateType.TIME_PACK)// 时间策略
									.disableOpenDir()// 禁止打开输出目录
									.outputDir(userDir + "/src/main/java") // 指定输出目录
									.commentDate("yyyy-MM-dd HH:mm:ss")// 注释日期
					).packageConfig(// 包配置
							builder -> builder.parent("com.demo") // 父包名
									.pathInfo(Collections.singletonMap(OutputFile.xml,
											userDir + "/src/main/resources/mapper")) // 路径配置信息
					).strategyConfig(// 策略配置
							builder -> builder.addInclude("user") // 增加表匹配
									.entityBuilder()// 实体策略配置
									.disableSerialVersionUID()// 禁用生成 serialVersionUID
									.enableLombok()// 开启 lombok 模型
									.enableChainModel()// 开启链式模型
									.enableFileOverride()// 覆盖已生成文件
									.mapperBuilder()// mapper 策略配置
									.mapperAnnotation(Mapper.class)// 设置 @Mapper 注解
									.enableFileOverride()// 覆盖已生成文件
					).templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板
					.templateConfig(// 模板配置
							builder -> builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE,
									TemplateType.SERVICE_IMPL)// 禁用模板
					).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
