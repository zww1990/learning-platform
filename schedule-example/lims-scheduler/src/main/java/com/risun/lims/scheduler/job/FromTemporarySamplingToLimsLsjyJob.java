package com.risun.lims.scheduler.job;

import com.risun.lims.scheduler.service.LimsLsjyService;
import com.risun.lims.scheduler.service.TemporarySamplingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FromTemporarySamplingToLimsLsjyJob {
    private final TemporarySamplingService temporarySamplingService;
    private final LimsLsjyService limsLsjyService;

    @Scheduled(cron = "0/10 * * * * *")
    public void execute() {
        log.info("execute(): count= {}", this.temporarySamplingService.selectCount());
        log.info("execute(): count= {}", this.limsLsjyService.selectCount());
    }
}
