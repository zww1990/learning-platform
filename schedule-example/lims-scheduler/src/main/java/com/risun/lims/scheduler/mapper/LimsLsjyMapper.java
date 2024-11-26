package com.risun.lims.scheduler.mapper;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * 数据库SQL映射接口
 * @author 张维维
 * @since 2024-11-26 15:19:02
 */
@Mapper
public interface LimsLsjyMapper {
    @Select("select count(1) from lims_lsjy")
    Integer selectCount();

    List<String> selectList(@Param("codeSet") Set<String> codeSet);

    int insertOne(TemporarySamplingItem item);
}
