-- 视频网站数据库
create database if not exists video_site;

create table if not exists t_user
(
    user_id       int(11) auto_increment primary key comment '用户主键',
    username      varchar(64)  not null unique comment '用户名',
    nickname      varchar(64)  not null comment '昵称',
    password      varchar(256) not null comment '密码',
    user_type     varchar(32)  not null comment '用户类型',
    created_date  datetime     not null comment '创建时间',
    creator       varchar(64)  not null comment '创建人',
    modified_date datetime     not null comment '修改时间',
    modifier      varchar(64)  not null comment '修改人'
) comment '用户表';

create table if not exists t_category
(
    category_id   int(11) auto_increment primary key comment '类别主键',
    category_name varchar(64) not null unique comment '类别名称',
    created_date  datetime    not null comment '创建时间',
    creator       varchar(64) not null comment '创建人',
    modified_date datetime    not null comment '修改时间',
    modifier      varchar(64) not null comment '修改人'
) comment '视频类别表';

create table if not exists t_video
(
    video_id      int(11) auto_increment primary key comment '视频主键',
    video_name    varchar(128) not null comment '视频名称',
    video_link    varchar(256) not null comment '视频链接',
    video_logo    varchar(256) not null comment '视频标志',
    video_hits    int          not null comment '视频点击率',
    category_id   int          not null comment '视频类别主键',
    created_date  datetime     not null comment '创建时间',
    creator       varchar(64)  not null comment '创建人',
    modified_date datetime     not null comment '修改时间',
    modifier      varchar(64)  not null comment '修改人',
    audit_status  varchar(32)  not null comment '审核状态',
    audited_date  datetime comment '审核时间',
    auditor       varchar(64) comment '审核人',
    audit_reason  varchar(256) comment '审核不通过原因',
    foreign key category_id_fk (category_id) references t_category (category_id)
) comment '视频表';

create table if not exists t_comment
(
    comment_id   int(11) auto_increment primary key comment '评论主键',
    content      varchar(256) not null comment '评论内容',
    video_id     int          not null comment '视频主键',
    created_date datetime     not null comment '创建时间',
    creator      varchar(64)  not null comment '创建人',
    foreign key video_id_fk (video_id) references t_video (video_id)
) comment '视频评论表';
