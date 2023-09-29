CREATE TABLE `tb_order` (
    `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编码',
    `status` smallint(3) DEFAULT NULL COMMENT '订单状态',
    `price` decimal(12,2) DEFAULT NULL COMMENT '价格',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_user` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
    `update_user` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';

/*Data for the table `tb_order` */

insert into `tb_order`
(`order_code`,`status`,`price`,`create_time`,`update_time`,`create_user`,`update_user`)
values
('A111',1,'22.00',now(),now(),'张三','张三'),
('A111',1,'22.00',now(),now(),'张三','张三'),
('A111',1,'22.00',now(),now(),'张三','张三'),
('A111',1,'22.00',now(),now(),'张三','张三');
