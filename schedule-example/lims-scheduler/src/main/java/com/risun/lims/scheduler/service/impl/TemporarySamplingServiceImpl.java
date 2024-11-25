package com.risun.lims.scheduler.service.impl;

import com.risun.lims.scheduler.config.DataSource;
import com.risun.lims.scheduler.config.DataSourceKey;
import com.risun.lims.scheduler.mapper.TemporarySamplingMapper;
import com.risun.lims.scheduler.service.TemporarySamplingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@DataSource(DataSourceKey.FIRST)
public class TemporarySamplingServiceImpl implements TemporarySamplingService {
    private final TemporarySamplingMapper temporarySamplingMapper;

    @Override
    public Integer selectCount() {
        return temporarySamplingMapper.selectCount();
    }
}
