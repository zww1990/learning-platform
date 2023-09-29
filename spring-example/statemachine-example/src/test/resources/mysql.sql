CREATE TABLE tb_order (
    id bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    order_code varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '订单编码',
    status smallint(3) DEFAULT NULL COMMENT '订单状态',
    price decimal(12,2) DEFAULT NULL COMMENT '价格',
    create_time timestamp NOT NULL COMMENT '创建时间',
    update_time timestamp NOT NULL COMMENT '更新时间',
    create_user varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
    update_user varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='订单表';
