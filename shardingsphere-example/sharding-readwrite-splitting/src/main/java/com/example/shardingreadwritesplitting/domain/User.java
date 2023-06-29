package com.example.shardingreadwritesplitting.domain;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
	private Integer id;
	private String nickname;
	private String password;
	private Integer sex;
	private LocalDate birthday;
}
