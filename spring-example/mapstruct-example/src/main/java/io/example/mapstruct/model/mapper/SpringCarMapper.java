package io.example.mapstruct.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import io.example.mapstruct.model.Car;
import io.example.mapstruct.model.CarDto;

/**
 * Spring Car Mapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午4:21:32
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface SpringCarMapper {

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
