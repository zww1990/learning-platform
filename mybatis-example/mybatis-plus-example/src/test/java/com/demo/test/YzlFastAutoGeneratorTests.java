package com.demo.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

@SpringBootTest
public class YzlFastAutoGeneratorTests {
	@Resource
	private DataSourceProperties properties;

	@Test
	public void testGenerator() {
		try {
			FastAutoGenerator.create(// 数据库配置
					this.properties.getUrl(), // jdbc 路径
					this.properties.getUsername(), // 数据库账号
					this.properties.getPassword()// 数据库密码
			).globalConfig(// 全局配置
					builder -> builder.author("javaer") // 作者名
							.dateType(DateType.ONLY_DATE)// 时间策略
							.disableOpenDir()// 禁止打开输出目录
							.commentDate("yyyy-MM-dd HH:mm:ss")// 注释日期
			).packageConfig(// 包配置
					builder -> {
						Map<OutputFile, String> path = new HashMap<>();
						path.put(OutputFile.xml,
								"D:\\projects\\yzl-order\\yzl-order-server\\src\\main\\resources\\mapper");
						path.put(OutputFile.entity,
								"D:\\projects\\yzl-order\\yzl-order-common\\src\\main\\java\\cn\\net\\yzl\\order\\model\\db\\order");
						path.put(OutputFile.mapper,
								"D:\\projects\\yzl-order\\yzl-order-server\\src\\main\\java\\cn\\net\\yzl\\order\\dao");
						builder.parent("cn.net.yzl.order") // 父包名
								.entity("model.db.order")// 实体包名
								.mapper("dao")// mapper接口包名
								.pathInfo(path) // 路径配置信息
						;
					}).strategyConfig(// 策略配置
							builder -> builder.addInclude("order_limit_record") // 增加表匹配
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
