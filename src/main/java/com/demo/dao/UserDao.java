package com.demo.dao;

import static com.demo.dao.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.demo.model.User;
import java.util.List;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
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
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.429+08:00", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.430+08:00", comments="Source Table: user")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.430+08:00", comments="Source Table: user")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.431+08:00", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    User selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.431+08:00", comments="Source Table: user")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.433+08:00", comments="Source Table: user")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.433+08:00", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<Long>> countByExample() {
        return SelectDSL.selectWithMapper(this::count, SqlBuilder.count())
                .from(user);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.434+08:00", comments="Source Table: user")
    default DeleteDSL<MyBatis3DeleteModelAdapter<Integer>> deleteByExample() {
        return DeleteDSL.deleteFromWithMapper(this::delete, user);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.434+08:00", comments="Source Table: user")
    default int deleteByPrimaryKey(Integer id_) {
        return DeleteDSL.deleteFromWithMapper(this::delete, user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.434+08:00", comments="Source Table: user")
    default int insert(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(id).toProperty("id")
                .map(name).toProperty("name")
                .map(age).toProperty("age")
                .map(birthday).toProperty("birthday")
                .map(address).toProperty("address")
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.435+08:00", comments="Source Table: user")
    default int insertSelective(User record) {
        return insert(SqlBuilder.insert(record)
                .into(user)
                .map(id).toPropertyWhenPresent("id", record::getId)
                .map(name).toPropertyWhenPresent("name", record::getName)
                .map(age).toPropertyWhenPresent("age", record::getAge)
                .map(birthday).toPropertyWhenPresent("birthday", record::getBirthday)
                .map(address).toPropertyWhenPresent("address", record::getAddress)
                .build()
                .render(RenderingStrategy.MYBATIS3));
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.436+08:00", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectByExample() {
        return SelectDSL.selectWithMapper(this::selectMany, id, name, age, birthday, address)
                .from(user);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.436+08:00", comments="Source Table: user")
    default QueryExpressionDSL<MyBatis3SelectModelAdapter<List<User>>> selectDistinctByExample() {
        return SelectDSL.selectDistinctWithMapper(this::selectMany, id, name, age, birthday, address)
                .from(user);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.437+08:00", comments="Source Table: user")
    default User selectByPrimaryKey(Integer id_) {
        return SelectDSL.selectWithMapper(this::selectOne, id, name, age, birthday, address)
                .from(user)
                .where(id, isEqualTo(id_))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.437+08:00", comments="Source Table: user")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExample(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(id).equalTo(record::getId)
                .set(name).equalTo(record::getName)
                .set(age).equalTo(record::getAge)
                .set(birthday).equalTo(record::getBirthday)
                .set(address).equalTo(record::getAddress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.438+08:00", comments="Source Table: user")
    default UpdateDSL<MyBatis3UpdateModelAdapter<Integer>> updateByExampleSelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(id).equalToWhenPresent(record::getId)
                .set(name).equalToWhenPresent(record::getName)
                .set(age).equalToWhenPresent(record::getAge)
                .set(birthday).equalToWhenPresent(record::getBirthday)
                .set(address).equalToWhenPresent(record::getAddress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.440+08:00", comments="Source Table: user")
    default int updateByPrimaryKey(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalTo(record::getName)
                .set(age).equalTo(record::getAge)
                .set(birthday).equalTo(record::getBirthday)
                .set(address).equalTo(record::getAddress)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.441+08:00", comments="Source Table: user")
    default int updateByPrimaryKeySelective(User record) {
        return UpdateDSL.updateWithMapper(this::update, user)
                .set(name).equalToWhenPresent(record::getName)
                .set(age).equalToWhenPresent(record::getAge)
                .set(birthday).equalToWhenPresent(record::getBirthday)
                .set(address).equalToWhenPresent(record::getAddress)
                .where(id, isEqualTo(record::getId))
                .build()
                .execute();
    }
}