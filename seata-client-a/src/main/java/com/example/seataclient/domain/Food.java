package com.example.seataclient.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 食品
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Food {
	/** 食品主键 */
	private Integer foodId;
	/** 食品名称 */
	private String foodName;
	/** 食品库存 */
	private Long stock;

}
