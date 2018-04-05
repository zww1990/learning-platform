package com.demo.dao;

import java.sql.JDBCType;
import java.util.Date;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    public static final User user = new User();

    public static final SqlColumn<Integer> id = user.id;

    public static final SqlColumn<String> name = user.name;

    public static final SqlColumn<Integer> age = user.age;

    public static final SqlColumn<Date> birthday = user.birthday;

    public static final SqlColumn<String> address = user.address;

    public static final SqlColumn<String> resume = user.resume;

    public static final class User extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);

        public final SqlColumn<Date> birthday = column("birthday", JDBCType.DATE);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> resume = column("resume", JDBCType.LONGVARCHAR);

        public User() {
            super("user");
        }
    }
}