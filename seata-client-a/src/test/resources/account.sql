create table account(
	user_id int(10) primary key not null auto_increment comment '主键',
	user_name varchar(20) not null comment '姓名',
	balance decimal(10,2) not null comment '余额'
)charset=utf8 comment='账户表';

insert into account (user_name, balance) values ('张三', 10000);

select * from account;
