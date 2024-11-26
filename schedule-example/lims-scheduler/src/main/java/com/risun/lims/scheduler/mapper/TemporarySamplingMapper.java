package com.risun.lims.scheduler.mapper;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TemporarySamplingMapper {
    @Select("select count(1) from temporary_sampling")
    Integer selectCount();

    List<TemporarySamplingItem> selectList();
}
