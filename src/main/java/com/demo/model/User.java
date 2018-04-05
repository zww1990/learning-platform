package com.demo.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;

/**
 * @author ZhangWeiWei
 * @description 
 * @date 2018-04-05 16:36:54
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.582+08:00", comments="Source field: user.id")
    private Integer id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.name")
    private String name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.age")
    private Integer age;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.birthday")
    private Date birthday;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.address")
    private String address;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.resume")
    private String resume;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.id")
    public Integer getId() {
        return id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.id")
    public void setId(Integer id) {
        this.id = id;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.name")
    public String getName() {
        return name;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.name")
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.585+08:00", comments="Source field: user.age")
    public Integer getAge() {
        return age;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.birthday")
    public Date getBirthday() {
        return birthday;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.birthday")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.address")
    public String getAddress() {
        return address;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.address")
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.resume")
    public String getResume() {
        return resume;
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-05T16:36:54.586+08:00", comments="Source field: user.resume")
    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }
}