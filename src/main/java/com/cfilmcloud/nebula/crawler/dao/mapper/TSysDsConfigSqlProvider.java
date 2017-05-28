package com.cfilmcloud.nebula.crawler.dao.mapper;

import com.cfilmcloud.nebula.crawler.model.TSysDsConfig;
import com.cfilmcloud.nebula.crawler.model.TSysDsConfigExample.Criteria;
import com.cfilmcloud.nebula.crawler.model.TSysDsConfigExample.Criterion;
import com.cfilmcloud.nebula.crawler.model.TSysDsConfigExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class TSysDsConfigSqlProvider {

    public String countByExample(TSysDsConfigExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_sys_ds_config");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(TSysDsConfigExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_sys_ds_config");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(TSysDsConfig record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_sys_ds_config");
        
        if (record.getRowId() != null) {
            sql.VALUES("ROW_ID", "#{rowId,jdbcType=INTEGER}");
        }
        
        if (record.getDataSourceId() != null) {
            sql.VALUES("DATA_SOURCE_ID", "#{dataSourceId,jdbcType=VARCHAR}");
        }
        
        if (record.getDataSourceName() != null) {
            sql.VALUES("DATA_SOURCE_NAME", "#{dataSourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMethod() != null) {
            sql.VALUES("RECEIVE_METHOD", "#{receiveMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getIsEnable() != null) {
            sql.VALUES("IS_ENABLE", "#{isEnable,jdbcType=INTEGER}");
        }
        
        if (record.getDataSourceDescribe() != null) {
            sql.VALUES("DATA_SOURCE_DESCRIBE", "#{dataSourceDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.VALUES("IS_DELETE", "#{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getDeleteTime() != null) {
            sql.VALUES("DELETE_TIME", "#{deleteTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("UPDATE_TIME", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.VALUES("CREATER", "#{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdater() != null) {
            sql.VALUES("UPDATER", "#{updater,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(TSysDsConfigExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("ROW_ID");
        } else {
            sql.SELECT("ROW_ID");
        }
        sql.SELECT("DATA_SOURCE_ID");
        sql.SELECT("DATA_SOURCE_NAME");
        sql.SELECT("RECEIVE_METHOD");
        sql.SELECT("IS_ENABLE");
        sql.SELECT("DATA_SOURCE_DESCRIBE");
        sql.SELECT("IS_DELETE");
        sql.SELECT("DELETE_TIME");
        sql.SELECT("CREATE_TIME");
        sql.SELECT("UPDATE_TIME");
        sql.SELECT("CREATER");
        sql.SELECT("UPDATER");
        sql.FROM("t_sys_ds_config");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        TSysDsConfig record = (TSysDsConfig) parameter.get("record");
        TSysDsConfigExample example = (TSysDsConfigExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_sys_ds_config");
        
        if (record.getRowId() != null) {
            sql.SET("ROW_ID = #{record.rowId,jdbcType=INTEGER}");
        }
        
        if (record.getDataSourceId() != null) {
            sql.SET("DATA_SOURCE_ID = #{record.dataSourceId,jdbcType=VARCHAR}");
        }
        
        if (record.getDataSourceName() != null) {
            sql.SET("DATA_SOURCE_NAME = #{record.dataSourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMethod() != null) {
            sql.SET("RECEIVE_METHOD = #{record.receiveMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getIsEnable() != null) {
            sql.SET("IS_ENABLE = #{record.isEnable,jdbcType=INTEGER}");
        }
        
        if (record.getDataSourceDescribe() != null) {
            sql.SET("DATA_SOURCE_DESCRIBE = #{record.dataSourceDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("IS_DELETE = #{record.isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getDeleteTime() != null) {
            sql.SET("DELETE_TIME = #{record.deleteTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{record.updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("CREATER = #{record.creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("UPDATER = #{record.updater,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_sys_ds_config");
        
        sql.SET("ROW_ID = #{record.rowId,jdbcType=INTEGER}");
        sql.SET("DATA_SOURCE_ID = #{record.dataSourceId,jdbcType=VARCHAR}");
        sql.SET("DATA_SOURCE_NAME = #{record.dataSourceName,jdbcType=VARCHAR}");
        sql.SET("RECEIVE_METHOD = #{record.receiveMethod,jdbcType=VARCHAR}");
        sql.SET("IS_ENABLE = #{record.isEnable,jdbcType=INTEGER}");
        sql.SET("DATA_SOURCE_DESCRIBE = #{record.dataSourceDescribe,jdbcType=VARCHAR}");
        sql.SET("IS_DELETE = #{record.isDelete,jdbcType=INTEGER}");
        sql.SET("DELETE_TIME = #{record.deleteTime,jdbcType=TIMESTAMP}");
        sql.SET("CREATE_TIME = #{record.createTime,jdbcType=TIMESTAMP}");
        sql.SET("UPDATE_TIME = #{record.updateTime,jdbcType=TIMESTAMP}");
        sql.SET("CREATER = #{record.creater,jdbcType=VARCHAR}");
        sql.SET("UPDATER = #{record.updater,jdbcType=VARCHAR}");
        
        TSysDsConfigExample example = (TSysDsConfigExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(TSysDsConfig record) {
        SQL sql = new SQL();
        sql.UPDATE("t_sys_ds_config");
        
        if (record.getDataSourceId() != null) {
            sql.SET("DATA_SOURCE_ID = #{dataSourceId,jdbcType=VARCHAR}");
        }
        
        if (record.getDataSourceName() != null) {
            sql.SET("DATA_SOURCE_NAME = #{dataSourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMethod() != null) {
            sql.SET("RECEIVE_METHOD = #{receiveMethod,jdbcType=VARCHAR}");
        }
        
        if (record.getIsEnable() != null) {
            sql.SET("IS_ENABLE = #{isEnable,jdbcType=INTEGER}");
        }
        
        if (record.getDataSourceDescribe() != null) {
            sql.SET("DATA_SOURCE_DESCRIBE = #{dataSourceDescribe,jdbcType=VARCHAR}");
        }
        
        if (record.getIsDelete() != null) {
            sql.SET("IS_DELETE = #{isDelete,jdbcType=INTEGER}");
        }
        
        if (record.getDeleteTime() != null) {
            sql.SET("DELETE_TIME = #{deleteTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreater() != null) {
            sql.SET("CREATER = #{creater,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdater() != null) {
            sql.SET("UPDATER = #{updater,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("ROW_ID = #{rowId,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, TSysDsConfigExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}