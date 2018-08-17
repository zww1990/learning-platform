package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.demo.service.JobService;

@Service
public class JobServiceImpl implements JobService {
	private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

	@Override
	public void execute(ShardingContext shardingContext) {
		log.info("{}", this);
	}
}
