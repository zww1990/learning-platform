create database sharding_config;

create database sharding_order_1;

CREATE TABLE t_order_info (
 order_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 user_id INT(11),
 product_name VARCHAR(128),
 quantity INT(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create database sharding_order_2;

CREATE TABLE t_order_info (
 order_id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
 user_id INT(11),
 product_name VARCHAR(128),
 quantity INT(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
