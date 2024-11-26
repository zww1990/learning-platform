package com.risun.lims.scheduler.mapper;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

@Mapper
public interface LimsLsjyMapper {
    @Select("select count(1) from lims_lsjy")
    Integer selectCount();

    List<String> selectList(@Param("codeSet") Set<String> codeSet);

    int insertOne(TemporarySamplingItem item);
}
