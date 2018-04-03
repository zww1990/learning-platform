package com.demo.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.426+08:00", comments="Source Table: user")
    public static final User user = new User();

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.428+08:00", comments="Source field: user.id")
    public static final SqlColumn<Integer> id = user.id;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.428+08:00", comments="Source field: user.name")
    public static final SqlColumn<String> name = user.name;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.428+08:00", comments="Source field: user.age")
    public static final SqlColumn<Integer> age = user.age;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.428+08:00", comments="Source field: user.birthday")
    public static final SqlColumn<Date> birthday = user.birthday;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.428+08:00", comments="Source field: user.address")
    public static final SqlColumn<String> address = user.address;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", date="2018-04-03T23:38:18.427+08:00", comments="Source Table: user")
    public static final class User extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);

        public final SqlColumn<Date> birthday = column("birthday", JDBCType.DATE);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public User() {
            super("user");
        }
    }
}