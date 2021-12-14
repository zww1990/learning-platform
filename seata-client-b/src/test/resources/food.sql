create table food(
	food_id int(10) primary key not null auto_increment comment '主键',
	food_name varchar(20) not null comment '食品名称',
	stock int(10) not null comment '库存'
)charset=utf8 comment='食品表';

insert into food (food_name, stock) values ('干脆面', 10000);

select * from food;
