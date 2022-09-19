package io.example.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;

/**
 * CarMapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午3:01:51
 */
@Mapper
public interface CarMapper {
	CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
