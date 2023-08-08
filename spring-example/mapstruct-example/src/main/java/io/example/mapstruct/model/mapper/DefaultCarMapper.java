package io.example.mapstruct.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.example.mapstruct.model.Car;
import io.example.mapstruct.model.CarDto;

/**
 * Default Car Mapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午3:01:51
 */
@Mapper
public interface DefaultCarMapper {
	DefaultCarMapper INSTANCE = Mappers.getMapper(DefaultCarMapper.class);

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
