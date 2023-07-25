create database if not exists example;

drop table if exists hello_world;

create table hello_world
(
    id           bigint unsigned auto_increment primary key comment '注解',
    say_hello    varchar(100) null comment '说话内容',
    your_name    varchar(100) null comment '姓名',
    gmt_created   datetime   DEFAULT NULL COMMENT '创建时间',
    gmt_modified datetime   DEFAULT NULL COMMENT '更新时间',
    is_deleted   tinyint(2) DEFAULT 0 COMMENT '是否逻辑删除'
) ENGINE = InnoDB CHARACTER SET = utf8 comment '简单演示表';
