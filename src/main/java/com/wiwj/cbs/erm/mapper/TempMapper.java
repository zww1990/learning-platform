package com.wiwj.cbs.erm.mapper;

import com.wiwj.cbs.erm.po.Temp;

public interface TempMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Temp record);

    int insertSelective(Temp record);

    Temp selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Temp record);

    int updateByPrimaryKey(Temp record);
}