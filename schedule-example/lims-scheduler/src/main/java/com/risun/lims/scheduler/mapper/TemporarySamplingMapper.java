package com.risun.lims.scheduler.mapper;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 数据库SQL映射接口
 * @author 张维维
 * @since 2024-11-26 15:19:34
 */
@Mapper
public interface TemporarySamplingMapper {
    @Select("select count(1) from temporary_sampling")
    Integer selectCount();

    List<TemporarySamplingItem> selectList();
}
