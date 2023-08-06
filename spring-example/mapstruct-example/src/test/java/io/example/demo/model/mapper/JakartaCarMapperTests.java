package io.example.demo.model.mapper;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;
import io.example.demo.model.CarType;

/**
 * Jakarta Car Mapper Tests
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月20日,上午11:26:38
 */
@SpringBootTest
public class JakartaCarMapperTests {
	@Inject
	private JakartaCarMapper jsrCarMapper;

	@Test
	public void testCarToCarDto() {
		try {
			Car car = new Car("雪佛兰", 5, CarType.SEDAN);
			System.err.println(car);
			CarDto carDto = this.jsrCarMapper.carToCarDto(car);
			System.err.println(carDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
