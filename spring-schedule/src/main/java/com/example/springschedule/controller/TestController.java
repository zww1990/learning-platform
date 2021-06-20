package com.example.springschedule.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springschedule.domain.QuartzModel;
import com.example.springschedule.service.QuartzJobService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private QuartzJobService quartzJobService;

	/**
	 * 添加新任务
	 */
	@PostMapping("/addjob")
	public Object addJob(@RequestBody QuartzModel model) {
		quartzJobService.addJob(model.getJobClass(), model.getJobName(), model.getGroupName(),
				model.getCronExpression(), model.getParam());
		return HttpStatus.OK;
	}

	/**
	 * 暂停任务
	 */
	@PostMapping("/pausejob")
	public Object pauseJob(@RequestBody QuartzModel model) {
		quartzJobService.pauseJob(model.getJobName(), model.getGroupName());
		return HttpStatus.OK;
	}

	/**
	 * 恢复任务
	 */
	@PostMapping("/resumejob")
	public Object resumeJob(@RequestBody QuartzModel model) {
		quartzJobService.resumeJob(model.getJobName(), model.getGroupName());
		return HttpStatus.OK;
	}

	/**
	 * 立即运行一次定时任务
	 */
	@PostMapping("/runonce")
	public Object runOnce(@RequestBody QuartzModel model) {
		quartzJobService.runOnce(model.getJobName(), model.getGroupName());
		return HttpStatus.OK;
	}

	/**
	 * 更新任务
	 */
	@PostMapping("/updatejob")
	public Object updateJob(@RequestBody QuartzModel model) {
		quartzJobService.updateJob(model.getJobName(), model.getGroupName(), model.getCronExpression(),
				model.getParam());
		return HttpStatus.OK;
	}

	/**
	 * 删除任务
	 */
	@PostMapping("/deletejob")
	public Object deleteJob(@RequestBody QuartzModel model) {
		quartzJobService.deleteJob(model.getJobName(), model.getGroupName());
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

	@GetMapping
	public Object index() {
		return Arrays.asList("hello", "world");
	}
}
