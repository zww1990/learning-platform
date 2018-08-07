package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.demo.service.JobBService;

@Service
public class JobBServiceImpl implements JobBService {
	private static final Logger log = LoggerFactory.getLogger(JobBServiceImpl.class);

	@Override
	public void execute(ShardingContext shardingContext) {
		log.info("{}", this);
	}
}
