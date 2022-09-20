package io.example.demo.model.mapper;

import org.junit.jupiter.api.Test;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;
import io.example.demo.model.CarType;

/**
 * Default Car Mapper Tests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午3:35:20
 */
public class DefaultCarMapperTests {

	@Test
	public void testCarToCarDto() {
		try {
			Car car = new Car("梅赛德斯奔驰", 5, CarType.SEDAN);
			System.err.println(car);
			CarDto carDto = DefaultCarMapper.INSTANCE.carToCarDto(car);
			System.err.println(carDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
