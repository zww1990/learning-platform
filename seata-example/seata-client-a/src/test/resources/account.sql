CREATE TABLE account (
  user_id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  user_name varchar(20) NOT NULL COMMENT '姓名',
  balance decimal(10,2) NOT NULL COMMENT '余额',
  PRIMARY KEY (user_id)
) CHARSET=utf8mb4 COMMENT='账户表';

insert into account (user_name, balance) values ('张三', 10000);

select * from account;
