CREATE TABLE food (
  food_id int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  food_name varchar(20) NOT NULL COMMENT '食品名称',
  stock int(10) NOT NULL COMMENT '库存',
  PRIMARY KEY (food_id)
) CHARSET=utf8mb4 COMMENT='食品表';

insert into food (food_name, stock) values ('干脆面', 10000);

select * from food;
