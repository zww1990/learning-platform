create table t_student(
 id int primary key auto_increment comment '编号',
 name varchar(20) not null comment '姓名',
 age int comment '年龄'
)Engine=InnoDB default charset=utf8 comment='学生表';

insert into t_student (name,age) values ('张三',11);
insert into t_student (name,age) values ('李四',12);

select * from t_student;
