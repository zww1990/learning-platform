package com.wiwj.cbs.erm.mapper;

import com.wiwj.cbs.erm.po.BasBusiness;

public interface BasBusinessMapper {
    int deleteByPrimaryKey(Long busiId);

    int insert(BasBusiness record);

    int insertSelective(BasBusiness record);

    BasBusiness selectByPrimaryKey(Long busiId);

    int updateByPrimaryKeySelective(BasBusiness record);

    int updateByPrimaryKey(BasBusiness record);
}