package com.example.schedulequartz.controller;

import com.example.schedulequartz.domain.QuartzModel;
import com.example.schedulequartz.service.QuartzJobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@AllArgsConstructor
public class DemoController {
    private QuartzJobService quartzJobService;

    /**
     * 添加新任务
     */
    @PostMapping("/addjob")
    public Object addJob(@RequestBody QuartzModel model) {
        quartzJobService.addJob(model);
        return HttpStatus.OK;
    }

    /**
     * 暂停任务
     */
    @PostMapping("/pausejob")
    public Object pauseJob(@RequestBody QuartzModel model) {
        quartzJobService.pauseJob(model);
        return HttpStatus.OK;
    }

    /**
     * 恢复任务
     */
    @PostMapping("/resumejob")
    public Object resumeJob(@RequestBody QuartzModel model) {
        quartzJobService.resumeJob(model);
        return HttpStatus.OK;
    }

    /**
     * 立即运行一次定时任务
     */
    @PostMapping("/runonce")
    public Object runOnce(@RequestBody QuartzModel model) {
        quartzJobService.runOnce(model);
        return HttpStatus.OK;
    }

    /**
     * 更新任务
     */
    @PostMapping("/updatejob")
    public Object updateJob(@RequestBody QuartzModel model) {
        quartzJobService.updateJob(model);
        return HttpStatus.OK;
    }

    /**
     * 删除任务
     */
    @PostMapping("/deletejob")
    public Object deleteJob(@RequestBody QuartzModel model) {
        quartzJobService.deleteJob(model);
        return HttpStatus.OK;
    }

    /**
     * 启动所有任务
     */
    @GetMapping("/startalljobs")
    public Object startAllJobs() {
        quartzJobService.startAllJobs();
        return HttpStatus.OK;
    }

    /**
     * 暂停所有任务
     */
    @GetMapping("/pausealljobs")
    public Object pauseAllJobs() {
        quartzJobService.pauseAllJobs();
        return HttpStatus.OK;
    }

    /**
     * 恢复所有任务
     */
    @GetMapping("/resumealljobs")
    public Object resumeAllJobs() {
        quartzJobService.resumeAllJobs();
        return HttpStatus.OK;
    }

    /**
     * 关闭所有任务
     */
    @GetMapping("/shutdownalljobs")
    public Object shutdownAllJobs() {
        quartzJobService.shutdownAllJobs();
        return HttpStatus.OK;
    }
}
