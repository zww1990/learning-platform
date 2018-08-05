package org.mybatis.generator.config;

import java.util.Arrays;

/**
 * @author ZhangWeiWei
 * @date 2018年4月8日,上午9:45:50
 * @description
 */
public enum MethodComments {
	/***  */
	deleteByPrimaryKey("按主键删除记录", "主键"),
	/***  */
	insert("插入记录", ""),
	/***  */
	insertSelective("选择性插入记录", ""),
	/***  */
	selectByPrimaryKey("按主键查询记录", "主键"),
	/***  */
	updateByPrimaryKeySelective("按主键选择性更新记录", ""),
	/***  */
	updateByPrimaryKeyWithBLOBs("按主键更新记录包含所有BLOB类型的字段", ""),
	/***  */
	updateByPrimaryKey("按主键更新记录", ""),
	/***  */
	count("查询计数", "查询语句"),
	/***  */
	delete("删除记录", "删除语句"),
	/***  */
	selectOne("查询一条记录", "查询语句"),
	/***  */
	selectMany("查询多条记录", "查询语句"),
	/***  */
	update("更新记录", "更新语句"),
	/***  */
	countByExample("返回表中与指定示例对象相匹配的行数", ""),
	/***  */
	deleteByExample("删除表中与指定示例对象相匹配的记录", ""),
	/***  */
	selectByExample("查询表中与指定示例对象相匹配的记录", ""),
	/***  */
	selectDistinctByExample("查询表中与指定示例对象相匹配的不重复记录", ""),
	/***  */
	updateByExample("更新表中与指定示例对象相匹配的记录", ""),
	/***  */
	updateByExampleSelective("选择性更新表中与指定示例对象相匹配的记录", ""),
	/***  */
	selectByExampleWithBLOBs("查询表中与指定示例对象相匹配的记录包含所有BLOB类型的字段", ""),
	/***  */
	updateByExampleWithBLOBs("更新表中与指定示例对象相匹配的记录包含所有BLOB类型的字段", "");
	private String comment;
	private String remark;

	private MethodComments(String comment, String remark) {
		this.comment = comment;
		this.remark = remark;
	}

	public String getComment() {
		return comment;
	}

	public String getRemark() {
		return remark;
	}

	public static MethodComments value(String name) {
		return Arrays.stream(values()).filter(x -> x.name().equals(name)).findFirst().orElseGet(() -> null);
	}
}
