CREATE TABLE bill (
  bill_id int(10) NOT NULL AUTO_INCREMENT COMMENT '账单主键',
  bill_num varchar(20) NOT NULL COMMENT '账单编号',
  user_id int(10) NOT NULL COMMENT '账户主键',
  food_id int(10) NOT NULL COMMENT '食品主键',
  order_time datetime NOT NULL comment '下单时间',
  amount decimal(10,2) NOT NULL COMMENT '金额',
  quantity int(10) NOT NULL COMMENT '数量',
  PRIMARY KEY (bill_id),
  UNIQUE KEY (bill_num)
) CHARSET=utf8mb4 COMMENT='账单表';

insert into bill (bill_num, user_id, food_id, order_time, amount, quantity) values ('202112181802', 1, 1, now(), 10, 5);

select * from bill;
