package com.stampede.changepwd.domain;

import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Attribute.Type;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

/**
 * @author ZhangWeiWei
 * @date 2020年2月15日,下午2:10:54
 * @description 人员数据模型
 */
@Entry(objectClasses = { "inetOrgPerson", "posixAccount", "top" })
public class Person {
	@Id
	private Name id;
	@Attribute(name = "uid")
	@DnAttribute(value = "uid")
	private String uid;
	@Attribute(name = "cn")
	private String cname;
	@Attribute(name = "sn")
	private String sname;
	@Attribute(name = "givenname")
	private String givenName;
	@Attribute(name = "gidnumber")
	private String gidNumber;
	@Attribute(name = "uidnumber")
	private String uidNumber;
	@Attribute(name = "mail")
	private String mail;
	@Attribute(name = "userpassword", type = Type.BINARY)
	private byte[] userPassword;

	/**
	 * @return 此注释将Java字段标记为包含LDAP条目的专有名称。
	 */
	public Name getId() {
		return id;
	}

	/**
	 * @return 主键
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @see #getUid()
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @see #getUid()
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @return 中文名称
	 */
	public String getGivenName() {
		return givenName;
	}

	/**
	 * @return 数字序列
	 */
	public String getGidNumber() {
		return gidNumber;
	}

	/**
	 * @return HR系统员工编号
	 */
	public String getUidNumber() {
		return uidNumber;
	}

	/**
	 * @return 电子邮箱
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return 密码
	 */
	public byte[] getUserPassword() {
		return userPassword;
	}

	/**
	 * @param id 此注释将Java字段标记为包含LDAP条目的专有名称。
	 */
	public void setId(Name id) {
		this.id = id;
	}

	/**
	 * @param uid 主键
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @see #setUid(String)
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * @see #setUid(String)
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @param givenName 中文名称
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	/**
	 * @param gidNumber 数字序列
	 */
	public void setGidNumber(String gidNumber) {
		this.gidNumber = gidNumber;
	}

	/**
	 * @param uidNumber HR系统员工编号
	 */
	public void setUidNumber(String uidNumber) {
		this.uidNumber = uidNumber;
	}

	/**
	 * @param mail 电子邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @param userPassword 密码
	 */
	public void setUserPassword(byte[] userPassword) {
		this.userPassword = userPassword;
	}
}
