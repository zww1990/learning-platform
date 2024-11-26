package com.risun.lims.scheduler.service;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;

import java.util.List;

/**
 * 服务接口
 * @author 张维维
 * @since 2024-11-26 15:20:09
 */
public interface TemporarySamplingService {
    Integer selectCount();

    List<TemporarySamplingItem> selectList();
}
