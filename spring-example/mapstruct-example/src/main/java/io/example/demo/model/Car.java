package io.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Car
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午2:55:18
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Car {
	private String make;
	private int numberOfSeats;
	private CarType type;
}
