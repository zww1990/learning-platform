package com.example.mybatisflex;

import java.util.List;
import java.util.stream.Stream;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

public class MybatisFlexCodegen {
	public static void main(String[] args) {
		// 配置数据源
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(
				"jdbc:mysql://localhost:3306/example?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		// 创建配置内容，两种风格都可以。
		GlobalConfig globalConfig = createGlobalConfigUseStyle1();
		// GlobalConfig globalConfig = createGlobalConfigUseStyle2();

		// 通过 datasource 和 globalConfig 创建代码生成器
		Generator generator = new Generator(dataSource, globalConfig);

		// 生成代码
		generator.generate();
	}

	public static GlobalConfig createGlobalConfigUseStyle1() {
		// 创建配置内容
		GlobalConfig globalConfig = new GlobalConfig();

		// 设置根包
		globalConfig.setBasePackage("com.example.mybatisflex");

		// 设置表前缀和只生成哪些表
//		globalConfig.setGenerateSchema("example");
		globalConfig.setTablePrefix("tb_");
		globalConfig.setGenerateTable("tb_account");

		// 设置生成 entity 并启用 Lombok
		globalConfig.setEntityGenerateEnable(true);
		globalConfig.setEntityWithLombok(true);
		globalConfig.setEntityInterfaces(List.of().toArray(Class[]::new));
		globalConfig.setEntityTemplatePath("/templates/entity.tpl");

		// 设置生成 mapper
		globalConfig.setMapperGenerateEnable(true);

		return globalConfig;
	}

	public static GlobalConfig createGlobalConfigUseStyle2() {
		// 创建配置内容
		GlobalConfig globalConfig = new GlobalConfig();

		// 设置根包
		globalConfig.getPackageConfig().setBasePackage("com.example.mybatisflex");

		// 设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
		globalConfig.getStrategyConfig()//
//				.setGenerateSchema("example")//
				.setTablePrefix("tb_")//
				.setGenerateTable("tb_account");

		// 设置生成 entity 并启用 Lombok
		globalConfig.enableEntity()//
				.setImplInterfaces(Stream.empty().toArray(Class[]::new))//
				.setWithLombok(true);
		globalConfig.getTemplateConfig().setEntity("/templates/entity.tpl");

		// 设置生成 mapper
		globalConfig.enableMapper();

		return globalConfig;
	}
}
