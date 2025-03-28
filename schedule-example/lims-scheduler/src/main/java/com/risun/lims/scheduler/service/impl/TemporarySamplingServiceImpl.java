package com.risun.lims.scheduler.service.impl;

import com.risun.lims.scheduler.config.DataSource;
import com.risun.lims.scheduler.config.DataSourceKey;
import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import com.risun.lims.scheduler.mapper.TemporarySamplingMapper;
import com.risun.lims.scheduler.service.TemporarySamplingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务接口实现类
 * @author 张维维
 * @since 2024-11-26 15:20:37
 */
@Service
@AllArgsConstructor
@DataSource(DataSourceKey.FIRST)
public class TemporarySamplingServiceImpl implements TemporarySamplingService {
    private final TemporarySamplingMapper temporarySamplingMapper;

    @Override
    public Integer selectCount() {
        return temporarySamplingMapper.selectCount();
    }

    @Override
    public List<TemporarySamplingItem> selectList() {
        return temporarySamplingMapper.selectList();
    }
}
