package io.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * CarDto
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午2:55:53
 */
@Getter
@Setter
@ToString
public class CarDto {
	private String make;
	private int seatCount;
	private String type;
}
