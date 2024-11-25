package com.risun.lims.scheduler.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LimsLsjyMapper {
    @Select("select count(1) from lims_lsjy")
    Integer selectCount();
}
