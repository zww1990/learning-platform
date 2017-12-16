package com.demo.nebula.crawler.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSysDsConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSysDsConfigExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andRowIdIsNull() {
            addCriterion("ROW_ID is null");
            return (Criteria) this;
        }

        public Criteria andRowIdIsNotNull() {
            addCriterion("ROW_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRowIdEqualTo(Integer value) {
            addCriterion("ROW_ID =", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotEqualTo(Integer value) {
            addCriterion("ROW_ID <>", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdGreaterThan(Integer value) {
            addCriterion("ROW_ID >", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ROW_ID >=", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdLessThan(Integer value) {
            addCriterion("ROW_ID <", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdLessThanOrEqualTo(Integer value) {
            addCriterion("ROW_ID <=", value, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdIn(List<Integer> values) {
            addCriterion("ROW_ID in", values, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotIn(List<Integer> values) {
            addCriterion("ROW_ID not in", values, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdBetween(Integer value1, Integer value2) {
            addCriterion("ROW_ID between", value1, value2, "rowId");
            return (Criteria) this;
        }

        public Criteria andRowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ROW_ID not between", value1, value2, "rowId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdIsNull() {
            addCriterion("DATA_SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdIsNotNull() {
            addCriterion("DATA_SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdEqualTo(String value) {
            addCriterion("DATA_SOURCE_ID =", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdNotEqualTo(String value) {
            addCriterion("DATA_SOURCE_ID <>", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdGreaterThan(String value) {
            addCriterion("DATA_SOURCE_ID >", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_ID >=", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdLessThan(String value) {
            addCriterion("DATA_SOURCE_ID <", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdLessThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_ID <=", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdLike(String value) {
            addCriterion("DATA_SOURCE_ID like", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdNotLike(String value) {
            addCriterion("DATA_SOURCE_ID not like", value, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdIn(List<String> values) {
            addCriterion("DATA_SOURCE_ID in", values, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdNotIn(List<String> values) {
            addCriterion("DATA_SOURCE_ID not in", values, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_ID between", value1, value2, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdNotBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_ID not between", value1, value2, "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameIsNull() {
            addCriterion("DATA_SOURCE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameIsNotNull() {
            addCriterion("DATA_SOURCE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameEqualTo(String value) {
            addCriterion("DATA_SOURCE_NAME =", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameNotEqualTo(String value) {
            addCriterion("DATA_SOURCE_NAME <>", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameGreaterThan(String value) {
            addCriterion("DATA_SOURCE_NAME >", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_NAME >=", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameLessThan(String value) {
            addCriterion("DATA_SOURCE_NAME <", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameLessThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_NAME <=", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameLike(String value) {
            addCriterion("DATA_SOURCE_NAME like", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameNotLike(String value) {
            addCriterion("DATA_SOURCE_NAME not like", value, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameIn(List<String> values) {
            addCriterion("DATA_SOURCE_NAME in", values, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameNotIn(List<String> values) {
            addCriterion("DATA_SOURCE_NAME not in", values, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_NAME between", value1, value2, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameNotBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_NAME not between", value1, value2, "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodIsNull() {
            addCriterion("RECEIVE_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodIsNotNull() {
            addCriterion("RECEIVE_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodEqualTo(String value) {
            addCriterion("RECEIVE_METHOD =", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodNotEqualTo(String value) {
            addCriterion("RECEIVE_METHOD <>", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodGreaterThan(String value) {
            addCriterion("RECEIVE_METHOD >", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodGreaterThanOrEqualTo(String value) {
            addCriterion("RECEIVE_METHOD >=", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodLessThan(String value) {
            addCriterion("RECEIVE_METHOD <", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodLessThanOrEqualTo(String value) {
            addCriterion("RECEIVE_METHOD <=", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodLike(String value) {
            addCriterion("RECEIVE_METHOD like", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodNotLike(String value) {
            addCriterion("RECEIVE_METHOD not like", value, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodIn(List<String> values) {
            addCriterion("RECEIVE_METHOD in", values, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodNotIn(List<String> values) {
            addCriterion("RECEIVE_METHOD not in", values, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodBetween(String value1, String value2) {
            addCriterion("RECEIVE_METHOD between", value1, value2, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodNotBetween(String value1, String value2) {
            addCriterion("RECEIVE_METHOD not between", value1, value2, "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("IS_ENABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("IS_ENABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(Integer value) {
            addCriterion("IS_ENABLE =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(Integer value) {
            addCriterion("IS_ENABLE <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(Integer value) {
            addCriterion("IS_ENABLE >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_ENABLE >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(Integer value) {
            addCriterion("IS_ENABLE <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(Integer value) {
            addCriterion("IS_ENABLE <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<Integer> values) {
            addCriterion("IS_ENABLE in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<Integer> values) {
            addCriterion("IS_ENABLE not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(Integer value1, Integer value2) {
            addCriterion("IS_ENABLE between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_ENABLE not between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeIsNull() {
            addCriterion("DATA_SOURCE_DESCRIBE is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeIsNotNull() {
            addCriterion("DATA_SOURCE_DESCRIBE is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeEqualTo(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE =", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeNotEqualTo(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE <>", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeGreaterThan(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE >", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE >=", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeLessThan(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE <", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeLessThanOrEqualTo(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE <=", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeLike(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE like", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeNotLike(String value) {
            addCriterion("DATA_SOURCE_DESCRIBE not like", value, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeIn(List<String> values) {
            addCriterion("DATA_SOURCE_DESCRIBE in", values, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeNotIn(List<String> values) {
            addCriterion("DATA_SOURCE_DESCRIBE not in", values, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_DESCRIBE between", value1, value2, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeNotBetween(String value1, String value2) {
            addCriterion("DATA_SOURCE_DESCRIBE not between", value1, value2, "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNull() {
            addCriterion("DELETE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIsNotNull() {
            addCriterion("DELETE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeEqualTo(Date value) {
            addCriterion("DELETE_TIME =", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotEqualTo(Date value) {
            addCriterion("DELETE_TIME <>", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThan(Date value) {
            addCriterion("DELETE_TIME >", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DELETE_TIME >=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThan(Date value) {
            addCriterion("DELETE_TIME <", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("DELETE_TIME <=", value, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeIn(List<Date> values) {
            addCriterion("DELETE_TIME in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotIn(List<Date> values) {
            addCriterion("DELETE_TIME not in", values, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeBetween(Date value1, Date value2) {
            addCriterion("DELETE_TIME between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andDeleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("DELETE_TIME not between", value1, value2, "deleteTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("CREATER is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("CREATER is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("CREATER =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("CREATER <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("CREATER >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("CREATER >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("CREATER <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("CREATER <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("CREATER like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("CREATER not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("CREATER in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("CREATER not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("CREATER between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("CREATER not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("UPDATER is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("UPDATER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("UPDATER =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("UPDATER <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("UPDATER >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("UPDATER <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("UPDATER <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("UPDATER like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("UPDATER not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("UPDATER in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("UPDATER not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("UPDATER between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("UPDATER not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andDataSourceIdLikeInsensitive(String value) {
            addCriterion("upper(DATA_SOURCE_ID) like", value.toUpperCase(), "dataSourceId");
            return (Criteria) this;
        }

        public Criteria andDataSourceNameLikeInsensitive(String value) {
            addCriterion("upper(DATA_SOURCE_NAME) like", value.toUpperCase(), "dataSourceName");
            return (Criteria) this;
        }

        public Criteria andReceiveMethodLikeInsensitive(String value) {
            addCriterion("upper(RECEIVE_METHOD) like", value.toUpperCase(), "receiveMethod");
            return (Criteria) this;
        }

        public Criteria andDataSourceDescribeLikeInsensitive(String value) {
            addCriterion("upper(DATA_SOURCE_DESCRIBE) like", value.toUpperCase(), "dataSourceDescribe");
            return (Criteria) this;
        }

        public Criteria andCreaterLikeInsensitive(String value) {
            addCriterion("upper(CREATER) like", value.toUpperCase(), "creater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLikeInsensitive(String value) {
            addCriterion("upper(UPDATER) like", value.toUpperCase(), "updater");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}