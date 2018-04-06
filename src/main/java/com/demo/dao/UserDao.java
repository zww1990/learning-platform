package com.demo.dao;

import static com.demo.dao.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.demo.model.User;
import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSL;
import org.mybatis.dynamic.sql.delete.MyBatis3DeleteModelAdapter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.MyBatis3SelectModelAdapter;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSL;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.MyBatis3UpdateModelAdapter;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

@Mapper
public interface UserDao {
    /**
     * @author zhangweiwei
     * @description 查询计数
     * @param selectStatement 查询语句
     * @date 2018-04-06 17:51:02
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    /**
     * @author zhangweiwei
     * @description 删除记录
     * @param deleteStatement 删除语句
     * @date 2018-04-06 17:51:02
     */
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    /**
     * @author zhangweiwei
     * @description 插入记录
     * @param insertStatement 
     * @date 2018-04-06 17:51:02
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.id", before=false, resultType=Integer.class)
    int insert(InsertStatementProvider<User> insertStatement);

    /**
     * @author zhangweiwei
     * @description 查询一条记录
     * @param selectStatement 查询语句
     * @date 2018-04-06 17:51:02
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    /**
     * @author zhangweiwei
     * @description 查询多条记录
     * @param selectStatement 查询语句
     * @date 2018-04-06 17:51:02
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="resume", property="resume", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    /**
     * @author zhangweiwei
     * @description 更新记录
     * @param updateStatement 更新语句
     * @date 2018-04-06 17:51:02
     */
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    /**
     * @author zhangweiwei
     * @description 返回表中与指定示例对象相匹配的行数
     * @date 2018-04-06 17:51:02
     */
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(user);
    }

    /**
     * @author zhangweiwei
     * @description 删除表中与指定示例对象相匹配的记录
     * @date 2018-04-06 17:51:02
     */
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, user);
    }

    /**
     * @author zhangweiwei
     * @description 按主键删除记录
     * @date 2018-04-06 17:51:02
     */
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /**
     * @author zhangweiwei
     * @description 插入记录
     * @date 2018-04-06 17:51:02
     */
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(name).toProperty("name")
                .map(age).toProperty("age")
                .map(birthday).toProperty("birthday")
                .map(address).toProperty("address")
                .map(resume).toProperty("resume")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    /**
     * @author zhangweiwei
     * @description 选择性插入记录
     * @date 2018-04-06 17:51:02
     */
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(age).toPropertyWhenPresent("age", record::getAge)
                .map(birthday).toPropertyWhenPresent("birthday", record::getBirthday)
                .map(address).toPropertyWhenPresent("address", record::getAddress)
                .map(resume).toPropertyWhenPresent("resume", record::getResume)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    /**
     * @author zhangweiwei
     * @description 查询表中与指定示例对象相匹配的记录
     * @date 2018-04-06 17:51:02
     */
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, age, birthday, address, resume)
                .from(user);
    }

    /**
     * @author zhangweiwei
     * @description 查询表中与指定示例对象相匹配的不重复记录
     * @date 2018-04-06 17:51:02
     */
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, age, birthday, address, resume)
                .from(user);
    }

    /**
     * @author zhangweiwei
     * @description 按主键查询记录
     * @date 2018-04-06 17:51:02
     */
    default User selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, age, birthday, address, resume)
                .from(user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    /**
     * @author zhangweiwei
     * @description 更新表中与指定示例对象相匹配的记录
     * @date 2018-04-06 17:51:02
     */
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalTo(record::getName)
                .set(age).equalTo(record::getAge)
                .set(birthday).equalTo(record::getBirthday)
                .set(address).equalTo(record::getAddress)
                .set(resume).equalTo(record::getResume);
    }

    /**
     * @author zhangweiwei
     * @description 选择性更新表中与指定示例对象相匹配的记录
     * @date 2018-04-06 17:51:02
     */
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalToWhenPresent(record::getName)
                .set(age).equalToWhenPresent(record::getAge)
                .set(birthday).equalToWhenPresent(record::getBirthday)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(resume).equalToWhenPresent(record::getResume);
    }

    /**
     * @author zhangweiwei
     * @description 按主键更新记录
     * @date 2018-04-06 17:51:02
     */
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalTo(record::getName)
                .set(age).equalTo(record::getAge)
                .set(birthday).equalTo(record::getBirthday)
                .set(address).equalTo(record::getAddress)
                .set(resume).equalTo(record::getResume)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    /**
     * @author zhangweiwei
     * @description 按主键选择性更新记录
     * @date 2018-04-06 17:51:02
     */
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalToWhenPresent(record::getName)
                .set(age).equalToWhenPresent(record::getAge)
                .set(birthday).equalToWhenPresent(record::getBirthday)
                .set(address).equalToWhenPresent(record::getAddress)
                .set(resume).equalToWhenPresent(record::getResume)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}