package com.example.shardingtablesplitting.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
	private Long orderId;
	private Integer userId;
	private String productName;
	private Integer quantity;
}
