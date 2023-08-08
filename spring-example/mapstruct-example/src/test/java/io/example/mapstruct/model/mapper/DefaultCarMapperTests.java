package io.example.mapstruct.model.mapper;

import io.example.mapstruct.model.Car;
import io.example.mapstruct.model.CarDto;
import io.example.mapstruct.model.CarType;
import org.junit.jupiter.api.Test;

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
