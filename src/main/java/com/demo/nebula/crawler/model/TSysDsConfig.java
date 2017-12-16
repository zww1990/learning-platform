package com.demo.nebula.crawler.model;

import java.io.Serializable;
import java.util.Date;

public class TSysDsConfig implements Serializable {
    private Integer rowId;

    private String dataSourceId;

    private String dataSourceName;

    private String receiveMethod;

    private Integer isEnable;

    private String dataSourceDescribe;

    private Integer isDelete;

    private Date deleteTime;

    private Date createTime;

    private Date updateTime;

    private String creater;

    private String updater;

    private static final long serialVersionUID = 1L;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId == null ? null : dataSourceId.trim();
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName == null ? null : dataSourceName.trim();
    }

    public String getReceiveMethod() {
        return receiveMethod;
    }

    public void setReceiveMethod(String receiveMethod) {
        this.receiveMethod = receiveMethod == null ? null : receiveMethod.trim();
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public String getDataSourceDescribe() {
        return dataSourceDescribe;
    }

    public void setDataSourceDescribe(String dataSourceDescribe) {
        this.dataSourceDescribe = dataSourceDescribe == null ? null : dataSourceDescribe.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }
}