package com.example.mybatisfluent.entity;

import java.time.LocalDateTime;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * HelloWorld: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@FluentMybatis(table = "hello_world", schema = "example", suffix = "", useDao = false, desc = "简单演示表")
@ToString
@Getter
@Setter
@Accessors(chain = true)
public class HelloWorld extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", desc = "主键")
	private Long id;

	@TableField(value = "say_hello", desc = "说话内容")
	private String sayHello;

	@TableField(value = "your_name", desc = "姓名")
	private String yourName;

	@TableField(value = "gmt_created", desc = "创建时间")
	private LocalDateTime gmtCreated;

	@TableField(value = "gmt_modified", desc = "更新时间")
	private LocalDateTime gmtModified;

	@TableField(value = "is_deleted", desc = "是否逻辑删除")
	private Integer isDeleted;

	@Override
	public final Class entityClass() {
		return HelloWorld.class;
	}
}
