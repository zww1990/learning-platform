package io.example.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.factory.Mappers;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;

/**
 * Default Car Mapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午3:01:51
 */
@Mapper(componentModel = ComponentModel.DEFAULT)
public interface DefaultCarMapper {
	DefaultCarMapper INSTANCE = Mappers.getMapper(DefaultCarMapper.class);

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
