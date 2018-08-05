package com.demo.dao;

import java.sql.JDBCType;
import java.time.LocalDate;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class UserDynamicSqlSupport {
    /*** 用户信息表 */
    public static final User user = new User();

    /*** 主键 */
    public static final SqlColumn<Integer> id = user.id;

    /*** 姓名 */
    public static final SqlColumn<String> name = user.name;

    /*** 年龄 */
    public static final SqlColumn<Integer> age = user.age;

    /*** 出生日期 */
    public static final SqlColumn<LocalDate> birthday = user.birthday;

    /*** 地址 */
    public static final SqlColumn<String> address = user.address;

    /*** 个人信息 */
    public static final SqlColumn<String> resume = user.resume;

    /**
     * @author zhangweiwei
     * @description 用户信息表
     * @date 2018-08-05 23:38:20
     */
    public static final class User extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<Integer> age = column("age", JDBCType.INTEGER);

        public final SqlColumn<LocalDate> birthday = column("birthday", JDBCType.DATE);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public final SqlColumn<String> resume = column("resume", JDBCType.VARCHAR);

        public User() {
            super("user");
        }
    }
}