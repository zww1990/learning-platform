package org.mybatis.generator.config;

import java.util.Arrays;

public enum MethodComments {
	/***  */
	deleteByPrimaryKey("按主键删除记录", true, "主键"),
	/***  */
	insert("插入记录", false, "插入语句"),
	/***  */
	insertSelective("选择性插入记录", false, ""),
	/***  */
	selectByPrimaryKey("按主键查询记录", true, "主键"),
	/***  */
	updateByPrimaryKeySelective("按主键选择性更新记录", false, ""),
	/***  */
	updateByPrimaryKeyWithBLOBs("按主键更新记录包含所有BLOB类型的字段", false, ""),
	/***  */
	updateByPrimaryKey("按主键更新记录", false, ""),
	/***  */
	count("查询计数", false, "查询语句"),
	/***  */
	delete("删除记录", false, "删除语句"),
	/***  */
	selectOne("查询一条记录", false, "查询语句"),
	/***  */
	selectMany("查询多条记录", false, "查询语句"),
	/***  */
	update("更新记录", false, "更新语句"),
	/***  */
	countByExample("返回表中与指定示例对象相匹配的行数", false, ""),
	/***  */
	deleteByExample("删除表中与指定示例对象相匹配的行数", false, ""),
	/***  */
	selectByExample("查询表中与指定示例对象相匹配的记录", false, ""),
	/***  */
	selectDistinctByExample("查询表中与指定示例对象相匹配的不重复记录", false, ""),
	/***  */
	updateByExample("更新表中与指定示例对象相匹配的行数", false, ""),
	/***  */
	updateByExampleSelective("选择性更新表中与指定示例对象相匹配的行数", false, "");
	private String comment;
	private boolean isPk;
	private String remark;

	private MethodComments(String comment, boolean isPk, String remark) {
		this.comment = comment;
		this.isPk = isPk;
		this.remark = remark;
	}

	public String getComment() {
		return comment;
	}

	public boolean isPk() {
		return isPk;
	}

	public String getRemark() {
		return remark;
	}

	public static MethodComments value(String name) {
		return Arrays.stream(values()).filter(x -> x.name().equals(name)).findFirst().orElse(null);
	}
}
