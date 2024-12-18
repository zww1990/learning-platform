-- 创建数据库
create database if not exists example;

-- 创建表
CREATE TABLE if not exists client_user (
  sequence 		int(10) 	NOT NULL 					PRIMARY KEY AUTO_INCREMENT 	COMMENT '序列',
  user_id 		varchar(64) NOT NULL 					UNIQUE KEY					COMMENT '用户唯一标识',
  username 		varchar(64) DEFAULT NULL 											COMMENT '姓名',
  phone_number 	varchar(64) DEFAULT NULL 											COMMENT '手机号',
  gender 		int(1) 		DEFAULT '0' 											COMMENT '0=未知,1=男,2=女',
  created_date 	datetime 	DEFAULT CURRENT_TIMESTAMP 								COMMENT '创建时间',
  modified_date datetime 	DEFAULT CURRENT_TIMESTAMP 	ON UPDATE CURRENT_TIMESTAMP	COMMENT '最后修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '用户表';
