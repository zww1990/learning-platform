package io.example.demo.model.mapper;

import org.junit.jupiter.api.Test;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;
import io.example.demo.model.CarType;

/**
 * CarMapperTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午3:35:20
 */
public class CarMapperTests {

	@Test
	public void testCarToCarDto() {
		try {
			Car car = new Car("Morris", 5, CarType.SEDAN);
			System.err.println(car);
			CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);
			System.err.println(carDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
