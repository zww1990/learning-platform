create table client_user
(
    user_id         varchar(64) primary key  not null comment '用户唯一标示',
    username        varchar(64)              null comment '名称',
    phone_number    varchar(64)              null comment '手机号',
    gender          integer(1) default 0     null comment '0=未知,1=男,2=女'
);
