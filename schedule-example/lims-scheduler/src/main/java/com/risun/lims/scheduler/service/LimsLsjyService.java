package com.risun.lims.scheduler.service;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;

import java.util.List;
import java.util.Set;

public interface LimsLsjyService {
    Integer selectCount();

    List<String> selectList(Set<String> codeSet);

    int insertOne(TemporarySamplingItem item);
}
