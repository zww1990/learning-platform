package io.example.demo.model.mapper;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;
import io.example.demo.model.CarType;

/**
 * CarMapperOfSpringTests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午4:24:02
 */
@SpringBootTest
public class CarMapperOfSpringTests {
	@Resource
	private CarMapperOfSpring carMapperOfSpring;

	@Test
	public void testCarToCarDto() {
		try {
			Car car = new Car("Morris", 5, CarType.SEDAN);
			System.err.println(car);
			CarDto carDto = this.carMapperOfSpring.carToCarDto(car);
			System.err.println(carDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
