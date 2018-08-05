package com.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zhangweiwei
 * @description 用户信息表
 * @date 2018-08-05 23:38:20
 */
@ApiModel(description = "用户信息表")
@SuppressWarnings("serial")
public class User implements Serializable {
    /*** 主键 */
    @ApiModelProperty(value = "主键")
    private Integer id;

    /*** 姓名 */
    @ApiModelProperty(value = "姓名")
    private String name;

    /*** 年龄 */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /*** 出生日期 */
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    /*** 地址 */
    @ApiModelProperty(value = "地址")
    private String address;

    /*** 个人信息 */
    @ApiModelProperty(value = "个人信息")
    private String resume;

    /**
     * @return 主键
     * @date 2018-08-05 23:38:20
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 主键
     * @date 2018-08-05 23:38:20
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 姓名
     * @date 2018-08-05 23:38:20
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 姓名
     * @date 2018-08-05 23:38:20
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 年龄
     * @date 2018-08-05 23:38:20
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age 年龄
     * @date 2018-08-05 23:38:20
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return 出生日期
     * @date 2018-08-05 23:38:20
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * @param birthday 出生日期
     * @date 2018-08-05 23:38:20
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * @return 地址
     * @date 2018-08-05 23:38:20
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address 地址
     * @date 2018-08-05 23:38:20
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return 个人信息
     * @date 2018-08-05 23:38:20
     */
    public String getResume() {
        return resume;
    }

    /**
     * @param resume 个人信息
     * @date 2018-08-05 23:38:20
     */
    public void setResume(String resume) {
        this.resume = resume == null ? null : resume.trim();
    }
}