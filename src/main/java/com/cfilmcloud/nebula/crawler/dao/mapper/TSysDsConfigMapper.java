package com.cfilmcloud.nebula.crawler.dao.mapper;

import com.cfilmcloud.nebula.crawler.model.TSysDsConfig;
import com.cfilmcloud.nebula.crawler.model.TSysDsConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TSysDsConfigMapper {
    @SelectProvider(type=TSysDsConfigSqlProvider.class, method="countByExample")
    long countByExample(TSysDsConfigExample example);

    @DeleteProvider(type=TSysDsConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(TSysDsConfigExample example);

    @Delete({
        "delete from t_sys_ds_config",
        "where ROW_ID = #{rowId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rowId);

    @Insert({
        "insert into t_sys_ds_config (ROW_ID, DATA_SOURCE_ID, ",
        "DATA_SOURCE_NAME, RECEIVE_METHOD, ",
        "IS_ENABLE, DATA_SOURCE_DESCRIBE, ",
        "IS_DELETE, DELETE_TIME, ",
        "CREATE_TIME, UPDATE_TIME, ",
        "CREATER, UPDATER)",
        "values (#{rowId,jdbcType=INTEGER}, #{dataSourceId,jdbcType=VARCHAR}, ",
        "#{dataSourceName,jdbcType=VARCHAR}, #{receiveMethod,jdbcType=VARCHAR}, ",
        "#{isEnable,jdbcType=INTEGER}, #{dataSourceDescribe,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=INTEGER}, #{deleteTime,jdbcType=TIMESTAMP}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{creater,jdbcType=VARCHAR}, #{updater,jdbcType=VARCHAR})"
    })
    int insert(TSysDsConfig record);

    @InsertProvider(type=TSysDsConfigSqlProvider.class, method="insertSelective")
    int insertSelective(TSysDsConfig record);

    @SelectProvider(type=TSysDsConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ROW_ID", property="rowId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DATA_SOURCE_ID", property="dataSourceId", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_SOURCE_NAME", property="dataSourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_METHOD", property="receiveMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_ENABLE", property="isEnable", jdbcType=JdbcType.INTEGER),
        @Result(column="DATA_SOURCE_DESCRIBE", property="dataSourceDescribe", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_TIME", property="deleteTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATER", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATER", property="updater", jdbcType=JdbcType.VARCHAR)
    })
    List<TSysDsConfig> selectByExample(TSysDsConfigExample example);

    @Select({
        "select",
        "ROW_ID, DATA_SOURCE_ID, DATA_SOURCE_NAME, RECEIVE_METHOD, IS_ENABLE, DATA_SOURCE_DESCRIBE, ",
        "IS_DELETE, DELETE_TIME, CREATE_TIME, UPDATE_TIME, CREATER, UPDATER",
        "from t_sys_ds_config",
        "where ROW_ID = #{rowId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROW_ID", property="rowId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="DATA_SOURCE_ID", property="dataSourceId", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_SOURCE_NAME", property="dataSourceName", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_METHOD", property="receiveMethod", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_ENABLE", property="isEnable", jdbcType=JdbcType.INTEGER),
        @Result(column="DATA_SOURCE_DESCRIBE", property="dataSourceDescribe", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_DELETE", property="isDelete", jdbcType=JdbcType.INTEGER),
        @Result(column="DELETE_TIME", property="deleteTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATER", property="creater", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATER", property="updater", jdbcType=JdbcType.VARCHAR)
    })
    TSysDsConfig selectByPrimaryKey(Integer rowId);

    @UpdateProvider(type=TSysDsConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TSysDsConfig record, @Param("example") TSysDsConfigExample example);

    @UpdateProvider(type=TSysDsConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TSysDsConfig record, @Param("example") TSysDsConfigExample example);

    @UpdateProvider(type=TSysDsConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TSysDsConfig record);

    @Update({
        "update t_sys_ds_config",
        "set DATA_SOURCE_ID = #{dataSourceId,jdbcType=VARCHAR},",
          "DATA_SOURCE_NAME = #{dataSourceName,jdbcType=VARCHAR},",
          "RECEIVE_METHOD = #{receiveMethod,jdbcType=VARCHAR},",
          "IS_ENABLE = #{isEnable,jdbcType=INTEGER},",
          "DATA_SOURCE_DESCRIBE = #{dataSourceDescribe,jdbcType=VARCHAR},",
          "IS_DELETE = #{isDelete,jdbcType=INTEGER},",
          "DELETE_TIME = #{deleteTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "CREATER = #{creater,jdbcType=VARCHAR},",
          "UPDATER = #{updater,jdbcType=VARCHAR}",
        "where ROW_ID = #{rowId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(TSysDsConfig record);
}