package com.example.schedulequartz.service;

import com.example.schedulequartz.domain.QuartzModel;

public interface QuartzJobService {
    /**
     * 添加任务可以传参数
     */
    void addJob(QuartzModel model);

    /**
     * 暂停任务
     */
    void pauseJob(QuartzModel model);

    /**
     * 恢复任务
     */
    void resumeJob(QuartzModel model);

    /**
     * 立即运行一次定时任务
     */
    void runOnce(QuartzModel model);

    /**
     * 更新任务
     */
    void updateJob(QuartzModel model);

    /**
     * 删除任务
     */
    void deleteJob(QuartzModel model);

    /**
     * 启动所有任务
     */
    void startAllJobs();

    /**
     * 暂停所有任务
     */
    void pauseAllJobs();

    /**
     * 恢复所有任务
     */
    void resumeAllJobs();

    /**
     * 关闭所有任务
     */
    void shutdownAllJobs();
}
