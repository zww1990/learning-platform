package com.example.test.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class UserLogin {
	private String userNo;
	private String password;
	private String address;
	private BigDecimal longitude;
	private BigDecimal latitude;
}
