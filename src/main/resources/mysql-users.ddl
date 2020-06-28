drop table t_user_authority;
drop table t_menu_authority;
drop table t_user;
drop table t_menu;
drop table t_authority;
drop table t_user_token;
DROP TABLE T_USER_SESSION_ATTRIBUTES;
DROP TABLE T_USER_SESSION;

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

create table t_menu (
menu_id integer not null auto_increment comment '菜单主键',
menu_name varchar(255) not null comment '菜单中文名称',
pattern_url varchar(255) not null comment '模式URL',
parent_menu_id integer comment '上级菜单主键',
enabled bit not null comment '菜单是否启用',
primary key (menu_id),
foreign key (parent_menu_id) references t_menu (menu_id)
) engine=InnoDB comment='菜单表';

create table t_user_authority (
user_id integer not null comment '用户主键',
auth_id integer not null comment '权限主键',
foreign key (user_id) references t_user (user_id) ON DELETE CASCADE,
foreign key (auth_id) references t_authority (auth_id) ON DELETE CASCADE
) engine=InnoDB comment='用户权限表';

create table t_menu_authority (
menu_id integer not null comment '菜单主键',
auth_id integer not null comment '权限主键',
foreign key (menu_id) references t_menu (menu_id) ON DELETE CASCADE,
foreign key (auth_id) references t_authority (auth_id) ON DELETE CASCADE
) engine=InnoDB comment='菜单权限表';

create table t_user_token (
series varchar(64) not null comment '系列号',
username varchar(255) not null comment '用户名',
token varchar(64) not null comment '自动登录访问令牌',
last_used timestamp not null comment '最后一次使用自动登录的时间',
primary key (series)
) engine=InnoDB comment='自动登录表';

CREATE TABLE T_USER_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL comment '主键编号',
	SESSION_ID CHAR(36) NOT NULL comment '会话编号',
	CREATION_TIME BIGINT NOT NULL comment '创建时间',
	LAST_ACCESS_TIME BIGINT NOT NULL comment '上次访问时间',
	MAX_INACTIVE_INTERVAL INT NOT NULL comment '最大不活动间隔',
	EXPIRY_TIME BIGINT NOT NULL comment '到期时间',
	PRINCIPAL_NAME VARCHAR(100) comment '用户名',
	PRIMARY KEY (PRIMARY_ID),
	UNIQUE INDEX (SESSION_ID),
	INDEX (EXPIRY_TIME),
	INDEX (PRINCIPAL_NAME)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC comment='用户会话表';

CREATE TABLE T_USER_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL comment '会话主键编号',
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL comment '属性名',
	ATTRIBUTE_BYTES BLOB NOT NULL comment '属性值',
	PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES T_USER_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC comment='用户会话属性表';

select * from t_authority;
select * from t_user;
select * from t_user_authority;
select * from t_menu;
select * from t_menu_authority;
select * from t_user_token;
select * from t_user_session;
select * from t_user_session_attributes;
