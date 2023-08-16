package com.example.schedulequartz.service.impl;

import com.example.schedulequartz.domain.QuartzModel;
import com.example.schedulequartz.service.QuartzJobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class QuartzJobServiceImpl implements QuartzJobService {
    private Scheduler scheduler;

    @SuppressWarnings("unchecked")
    @Override
    public void addJob(QuartzModel model) {
        try {
            // 启动调度器，默认初始化的时候已经启动
//            scheduler.start();
            // 构建job信息
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(model.getJobClass());
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(model.getJobName(), model.getGroupName()).build();
            // 表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(model.getCronExpression());
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(model.getJobName(), model.getGroupName())
                    .withSchedule(scheduleBuilder).build();
            // 获得JobDataMap，写入数据
            if (model.getParam() != null) {
                trigger.getJobDataMap().putAll(model.getParam());
            }
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("创建任务失败", e);
        }
    }

    @Override
    public void pauseJob(QuartzModel model) {
        try {
            scheduler.pauseJob(JobKey.jobKey(model.getJobName(), model.getGroupName()));
        } catch (SchedulerException e) {
            log.error("暂停任务失败", e);
        }
    }

    @Override
    public void resumeJob(QuartzModel model) {
        try {
            scheduler.resumeJob(JobKey.jobKey(model.getJobName(), model.getGroupName()));
        } catch (SchedulerException e) {
            log.error("恢复任务失败", e);
        }
    }

    @Override
    public void runOnce(QuartzModel model) {
        try {
            scheduler.triggerJob(JobKey.jobKey(model.getJobName(), model.getGroupName()));
        } catch (SchedulerException e) {
            log.error("立即运行一次定时任务失败", e);
        }
    }

    @Override
    public void updateJob(QuartzModel model) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(model.getJobName(), model.getGroupName());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (model.getCronExpression() != null) {
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(model.getCronExpression());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            }
            // 修改map
            if (model.getParam() != null) {
                trigger.getJobDataMap().putAll(model.getParam());
            }
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (Exception e) {
            log.error("更新任务失败", e);
        }
    }

    @Override
    public void deleteJob(QuartzModel model) {
        try {
            TriggerKey tk = TriggerKey.triggerKey(model.getJobName(), model.getGroupName());
            // 暂停、移除、删除
            scheduler.pauseTrigger(tk);
            scheduler.unscheduleJob(tk);
            scheduler.deleteJob(JobKey.jobKey(model.getJobName(), model.getGroupName()));
        } catch (Exception e) {
            log.error("删除任务失败", e);
        }
    }

    @Override
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            log.error("开启所有的任务失败", e);
        }
    }

    @Override
    public void pauseAllJobs() {
        try {
            scheduler.pauseAll();
        } catch (Exception e) {
            log.error("暂停所有任务失败", e);
        }
    }

    @Override
    public void resumeAllJobs() {
        try {
            scheduler.resumeAll();
        } catch (Exception e) {
            log.error("恢复所有任务失败", e);
        }
    }

    @Override
    public void shutdownAllJobs() {
        try {
            if (!scheduler.isShutdown()) {
                // 需谨慎操作关闭scheduler容器
                // scheduler生命周期结束，无法再 start() 启动scheduler
                scheduler.shutdown(true);
            }
        } catch (Exception e) {
            log.error("关闭所有的任务失败", e);
        }
    }
}
