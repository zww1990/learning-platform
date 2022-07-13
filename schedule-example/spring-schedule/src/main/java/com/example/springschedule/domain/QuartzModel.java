package com.example.springschedule.domain;

import java.util.Map;

public class QuartzModel {

	/**
	 * 任务名称
	 */
	private String jobName;

	/**
	 * 任务所属组
	 */
	private String groupName;

	/**
	 * 任务执行类
	 */
	private String jobClass;

	/**
	 * 任务调度时间表达式
	 */
	private String cronExpression;

	/**
	 * 附加参数
	 */
	private Map<String, Object> param;

	public QuartzModel() {
		super();
	}

	public String getJobName() {
		return jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getJobClass() {
		return jobClass;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public Map<String, Object> getParam() {
		return param;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public void setParam(Map<String, Object> param) {
		this.param = param;
	}

}