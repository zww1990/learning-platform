package com.stampede.changepwd.domain;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;
import org.springframework.util.StringUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author ZhangWeiWei
 * @date 2020年2月15日,下午2:10:54
 * @description 人员数据模型
 */
@Entry(objectClasses = { "inetOrgPerson", "posixAccount", "top" })
@ToString
@Setter
@Getter
@Accessors(chain = true)
public class Person {
	/**
	 * 此注释将Java字段标记为包含LDAP条目的专有名称。
	 */
	@Id
	private Name id;
	/**
	 * 主键
	 */
	@Attribute(name = "uid")
	private String uid;
	/**
	 * 主键
	 */
	@Attribute(name = "cn")
	private String cname;
	/**
	 * 主键
	 */
	@Attribute(name = "sn")
	private String sname;
	/**
	 * 中文名称
	 */
	@Attribute(name = "givenname")
	private String givenName;
	/**
	 * 数字序列
	 */
	@Attribute(name = "gidnumber")
	private String gidNumber;
	/**
	 * HR系统员工编号
	 */
	@Attribute(name = "uidnumber")
	private String uidNumber;
	/**
	 * 电子邮箱
	 */
	@Attribute(name = "mail")
	private String mail;
	/**
	 * 密码
	 */
	@Attribute(name = "userpassword")
	private String userPassword;
	@Transient
	private String homeDirectory = "/home/users/";

	public String getHomeDirectory() {
		if (StringUtils.hasText(this.getUid())) {
			homeDirectory += this.getUid();
		}
		return homeDirectory;
	}

	/**
	 * @return 主键
	 */
	public String getUid() {
		return uid.trim();
	}

	/**
	 * @see #getUid()
	 */
	public String getCname() {
		return cname.trim();
	}

	/**
	 * @see #getUid()
	 */
	public String getSname() {
		return sname.trim();
	}

	/**
	 * @return 中文名称
	 */
	public String getGivenName() {
		return givenName.trim();
	}

	/**
	 * @return 数字序列
	 */
	public String getGidNumber() {
		return gidNumber.trim();
	}

	/**
	 * @return HR系统员工编号
	 */
	public String getUidNumber() {
		return uidNumber.trim();
	}

	/**
	 * @return 电子邮箱
	 */
	public String getMail() {
		return mail.trim();
	}

	/**
	 * @return 密码
	 */
	public String getUserPassword() {
		return userPassword.trim();
	}

}
