package io.example.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import io.example.demo.model.Car;
import io.example.demo.model.CarDto;

/**
 * Jsr Car Mapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月20日,上午11:24:24
 */
@Mapper(componentModel = ComponentModel.JSR330)
public interface JsrCarMapper {

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
