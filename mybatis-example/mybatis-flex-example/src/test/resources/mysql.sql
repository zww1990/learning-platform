CREATE TABLE tb_account (
  id int(11) unsigned NOT NULL AUTO_INCREMENT comment '主键',
  user_name varchar(64) DEFAULT NULL comment '用户名',
  age int(11) DEFAULT NULL comment '年龄',
  birthday datetime DEFAULT NULL comment '生日',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '账户表';
