package com.risun.lims.scheduler.service.impl;

import com.risun.lims.scheduler.config.DataSource;
import com.risun.lims.scheduler.config.DataSourceKey;
import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import com.risun.lims.scheduler.mapper.LimsLsjyMapper;
import com.risun.lims.scheduler.service.LimsLsjyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 服务接口实现类
 * @author 张维维
 * @since 2024-11-26 15:20:30
 */
@Service
@AllArgsConstructor
@DataSource(DataSourceKey.SECOND)
public class LimsLsjyServiceImpl implements LimsLsjyService {
    private final LimsLsjyMapper limsLsjyMapper;

    @Override
    public Integer selectCount() {
        return limsLsjyMapper.selectCount();
    }

    @Override
    public List<String> selectList(Set<String> codeSet) {
        return limsLsjyMapper.selectList(codeSet);
    }

    @Override
    public int insertOne(TemporarySamplingItem item) {
        return limsLsjyMapper.insertOne(item);
    }
}
