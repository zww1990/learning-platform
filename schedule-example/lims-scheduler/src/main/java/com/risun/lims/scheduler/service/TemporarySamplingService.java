package com.risun.lims.scheduler.service;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;

import java.util.List;

public interface TemporarySamplingService {
    Integer selectCount();

    List<TemporarySamplingItem> selectList();
}
