package io.example.mapstruct.model.mapper;

import io.example.mapstruct.model.Car;
import io.example.mapstruct.model.CarDto;
import io.example.mapstruct.model.CarType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Car Mapper Tests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午4:24:02
 */
@SpringBootTest
public class SpringCarMapperTests {
	@Autowired
	private SpringCarMapper springCarMapper;

	@Test
	public void testCarToCarDto() {
		try {
			Car car = new Car("一汽大众", 5, CarType.SEDAN);
			System.err.println(car);
			CarDto carDto = this.springCarMapper.carToCarDto(car);
			System.err.println(carDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
