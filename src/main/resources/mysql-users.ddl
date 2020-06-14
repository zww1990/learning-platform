drop table t_user_authority;
drop table t_user;
drop table t_authority;
drop table t_user_token;

create table t_authority (
auth_id integer not null auto_increment comment '权限主键', 
auth_name varchar(255) not null comment '权限中文名称', 
authority varchar(255) not null comment '权限编码', 
primary key (auth_id)
) engine=InnoDB comment='权限表';

create table t_user (
user_id integer not null auto_increment comment '用户主键', 
account_non_expired bit not null comment '帐户是否过期', 
account_non_locked bit not null comment '帐户是否锁定', 
credentials_non_expired bit not null comment '密码是否过期', 
enabled bit not null comment '帐户是否启用', 
password varchar(255) not null comment '密码', 
username varchar(255) not null comment '用户名', 
primary key (user_id)
) engine=InnoDB comment='用户表';

create table t_user_authority (
user_id integer not null comment '用户主键', 
auth_id integer not null comment '权限主键',
foreign key (user_id) references t_user (user_id),
foreign key (auth_id) references t_authority (auth_id)
) engine=InnoDB comment='用户权限表';

create table t_user_token (
series varchar(64) primary key comment '系列号', 
username varchar(255) not null comment '用户名', 
token varchar(64) not null comment '自动登录访问令牌', 
last_used timestamp not null comment '最后一次使用自动登录的时间'
) engine=InnoDB comment='自动登录表';

select * from t_authority;
select * from t_user;
select * from t_user_authority;
select * from t_user_token;
