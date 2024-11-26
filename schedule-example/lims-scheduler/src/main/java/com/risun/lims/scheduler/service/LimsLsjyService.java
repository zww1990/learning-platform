package com.risun.lims.scheduler.service;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;

import java.util.List;
import java.util.Set;

/**
 * 服务接口
 * @author 张维维
 * @since 2024-11-26 15:20:02
 */
public interface LimsLsjyService {
    Integer selectCount();

    List<String> selectList(Set<String> codeSet);

    int insertOne(TemporarySamplingItem item);
}
