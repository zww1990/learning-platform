package com.risun.lims.scheduler.job;

import com.risun.lims.scheduler.domain.TemporarySamplingItem;
import com.risun.lims.scheduler.service.LimsLsjyService;
import com.risun.lims.scheduler.service.TemporarySamplingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 定时任务处理类
 * @author 张维维
 * @since 2024-11-26 15:17:27
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "jobs.from-temporarysampling-to-limslsjy.enabled", havingValue = "true")
public class FromTemporarySamplingToLimsLsjyJob {
    private final TemporarySamplingService temporarySamplingService;
    private final LimsLsjyService limsLsjyService;

    public FromTemporarySamplingToLimsLsjyJob(TemporarySamplingService temporarySamplingService, LimsLsjyService limsLsjyService) {
        this.temporarySamplingService = temporarySamplingService;
        this.limsLsjyService = limsLsjyService;
        log.info("FromTemporarySamplingToLimsLsjyJob(): 定时任务已开启");
    }

    @Scheduled(cron = "${jobs.from-temporarysampling-to-limslsjy.cron}")
    public void execute() {
        // 查询当天待处理的数据
        List<TemporarySamplingItem> itemList = this.temporarySamplingService.selectList();
        log.info("execute(): Temporary Sampling Item Size = {}", itemList.size());
        if (itemList.isEmpty()) {
            log.info("execute(): 没有待处理的数据。");
        } else {
            // 收集待处理数据的编码
            Set<String> codeSet = itemList.stream().map(TemporarySamplingItem::getTempCode).collect(Collectors.toSet());
            // 查询已处理数据的编码
            List<String> codeList = this.limsLsjyService.selectList(codeSet);
            log.info("execute(): Code Size = {}", codeList.size());
            int sum = itemList.stream().mapToInt(item -> {
                if (codeList.contains(item.getTempCode())) {
                    // 如果待处理数据已经被处理过了，就不再处理了
                    return 0;
                } else {
                    // 如果待处理数据没被处理，则进行处理
                    return this.limsLsjyService.insertOne(item);
                }
            }).sum();
            if (sum == 0) {
                log.info("execute(): 没有待处理的数据。");
            } else {
                log.info("execute(): Insert Rows = {}", sum);
            }
        }
    }
}
