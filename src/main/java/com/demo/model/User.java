package com.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ZhangWeiWei
 * @description 用户信息表
 * @date 2018-04-05 18:28:16
 */
@SuppressWarnings("serial")
public class User implements Serializable {
    private Integer id;

    private String name;

    private Integer age;

    private Date birthday;

    private String address;

    private String resume;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }
}