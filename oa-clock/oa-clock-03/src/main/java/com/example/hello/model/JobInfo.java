package com.example.hello.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * JobInfo
 * 
 * @author zhang weiwei
 * @since 2022年8月7日,下午1:07:00
 */
@Getter
@Setter
@ToString
public class JobInfo {
	private List<UserLogin> users;
	private String cronExpression;
}
