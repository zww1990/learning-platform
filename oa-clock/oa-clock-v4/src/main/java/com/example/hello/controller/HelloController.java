package com.example.hello.controller;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hello.model.ApplicationProperties;
import com.example.hello.model.ApplicationProperties.Address;
import com.example.hello.model.ApplicationProperties.JobConfig;
import com.example.hello.model.ApplicationProperties.UserInfo;
import com.example.hello.model.JobInfo;
import com.example.hello.model.ResponseBody;
import com.example.hello.model.UserLogin;
import com.example.hello.service.HelloService;

/**
 * HelloController
 * 
 * @author zhang weiwei
 * @since 2022年8月6日,下午4:16:05
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
	@Resource
	private HelloService helloService;
	@Resource
	private Scheduler scheduler;
	@Resource
	private ApplicationProperties properties;
	@Resource
	private JobDetail jobDetail;

	@PostMapping("/job")
	public ResponseBody<?> job(JobInfo jobInfo) throws SchedulerException {
		JobConfig config = this.properties.getJobConfig();
		TriggerState state = this.scheduler.getTriggerState(TriggerKey.triggerKey(config.getTriggerKey()));
		// 不存在，创建
		if (state == TriggerState.NONE) {
			if (jobInfo == null) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("参数不能为空！");
			}
			if (CollectionUtils.isEmpty(jobInfo.getUsers())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[users]不能为空！");
			}
			if (!StringUtils.hasText(jobInfo.getCronExpression())) {
				return new ResponseBody<>()//
						.setCode(HttpStatus.BAD_REQUEST.value())//
						.setStatus(ResponseBody.FAILURE)//
						.setMessage("[cronExpression]不能为空！");
			}
			CronTrigger trigger = TriggerBuilder.newTrigger()//
					.forJob(this.jobDetail)//
					.withIdentity(config.getTriggerKey())//
					.withSchedule(CronScheduleBuilder.cronSchedule(jobInfo.getCronExpression()))//
					.build();
			trigger.getJobDataMap().put(config.getJobDataKey(), jobInfo.getUsers());
			this.scheduler.scheduleJob(this.jobDetail, trigger);
			return new ResponseBody<>()//
					.setCode(HttpStatus.OK.value())//
					.setStatus(ResponseBody.SUCCESS)//
					.setMessage("创建定时任务成功");
		}
		// 正常
		if (state == TriggerState.NORMAL) {
			return null;
		}
		// 暂停
		if (state == TriggerState.PAUSED) {
			return null;
		}
		// 完成
		if (state == TriggerState.COMPLETE) {
			return null;
		}
		// 错误
		if (state == TriggerState.ERROR) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage(String.format("当前任务状态[%s]错误", state));
		}
		// 阻塞，处理中
		if (state == TriggerState.BLOCKED) {
			return new ResponseBody<>()//
					.setCode(HttpStatus.BAD_REQUEST.value())//
					.setStatus(ResponseBody.FAILURE)//
					.setMessage(String.format("当前任务状态[%s]处理中", state));
		}
		// 不在以上任何状态之内
		return new ResponseBody<>()//
				.setCode(HttpStatus.BAD_REQUEST.value())//
				.setStatus(ResponseBody.FAILURE)//
				.setMessage(String.format("未知的任务状态[%s]", state));
	}

	@GetMapping("/users")
	public ResponseBody<List<UserInfo>> getUsers() {
		return this.helloService.getUsers();
	}

	@GetMapping("/addresses")
	public ResponseBody<List<Address>> getAddresses() {
		return this.helloService.getAddresses();
	}

	@PostMapping("/saveaddress")
	public ResponseBody<?> saveAddress(@RequestBody Address address) {
		return this.helloService.saveAddress(address);
	}

	@PostMapping("/initstaffclock")
	public ResponseBody<?> initStaffClock(@RequestBody UserLogin userLogin) {
		return this.helloService.initStaffClock(userLogin);
	}

	@PostMapping("/v2/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV2(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV2(userLogin);
	}

	@PostMapping("/v1/userloginandstaffclock")
	public ResponseBody<?> userLoginAndStaffClockV1(@RequestBody UserLogin userLogin) {
		return this.helloService.userLoginAndStaffClockV1(userLogin);
	}

	@PostMapping("/selectappstaffclockloglist")
	public ResponseBody<?> selectAppStaffClockLogList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectAppStaffClockLogList(userLogin);
	}

	@PostMapping("/selectdevicelist")
	public ResponseBody<?> selectDeviceList(@RequestBody UserLogin userLogin) {
		return this.helloService.selectDeviceList(userLogin);
	}

	@PostMapping("/resetbinddevice")
	public ResponseBody<?> resetBindDevice(@RequestParam String staffNo, @RequestParam Integer id) {
		return this.helloService.resetBindDevice(staffNo, id);
	}
}
