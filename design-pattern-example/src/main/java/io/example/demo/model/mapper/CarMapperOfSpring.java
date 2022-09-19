package io.example.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;

/**
 * CarMapperOfSpring
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月19日,下午4:21:32
 */
@Mapper(componentModel = ComponentModel.SPRING)
public interface CarMapperOfSpring {
	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
