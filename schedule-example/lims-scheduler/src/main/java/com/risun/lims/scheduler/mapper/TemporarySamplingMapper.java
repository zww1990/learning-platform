package com.risun.lims.scheduler.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TemporarySamplingMapper {
    @Select("select count(1) from temporary_sampling")
    Integer selectCount();
}
