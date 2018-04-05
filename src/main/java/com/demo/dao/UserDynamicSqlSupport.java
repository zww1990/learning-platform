package com.demo.dao;

import java.sql.JDBCType;
import java.util.Date;
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
    public static final SqlColumn<Date> birthday = user.birthday;

    /*** 地址 */
    public static final SqlColumn<String> address = user.address;

    /*** 个人简历 */
    public static final SqlColumn<String> resume = user.resume;

    /**
     * @author ZhangWeiWei
     * @description 用户信息表
     * @date 2018-04-05 21:26:00
     */
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