create database sharding_config;
create database sharding_write;
create database sharding_read;

CREATE TABLE t_user (
  id int(11) NOT NULL AUTO_INCREMENT,
  nickname varchar(100) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  sex int(11) DEFAULT NULL,
  birthday date DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
