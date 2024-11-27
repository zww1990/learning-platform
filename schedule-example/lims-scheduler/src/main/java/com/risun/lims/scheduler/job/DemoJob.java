package com.risun.lims.scheduler.job;

import com.risun.lims.scheduler.service.LimsLsjyService;
import com.risun.lims.scheduler.service.TemporarySamplingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务处理类
 * @author 张维维
 * @since 2024-11-27 10:25:30
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "jobs.demo.enabled", havingValue = "true")
public class DemoJob {
    private final TemporarySamplingService temporarySamplingService;
    private final LimsLsjyService limsLsjyService;

    public DemoJob(TemporarySamplingService temporarySamplingService, LimsLsjyService limsLsjyService) {
        this.temporarySamplingService = temporarySamplingService;
        this.limsLsjyService = limsLsjyService;
        log.info("DemoJob(): 定时任务已开启");
    }

    @Scheduled(cron = "${jobs.demo.cron}")
    public void execute() {
        log.info("execute(): count = {}", temporarySamplingService.selectCount());
        log.info("execute(): count = {}", limsLsjyService.selectCount());
    }
}
