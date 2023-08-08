package io.example.mapstruct.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

import io.example.mapstruct.model.Car;
import io.example.mapstruct.model.CarDto;

/**
 * Jakarta Car Mapper
 * 
 * @author weiwei
 * @version v1
 * @since 2022年9月20日,上午11:07:23
 */
@Mapper(componentModel = ComponentModel.JAKARTA)
public interface JakartaCarMapper {

	@Mapping(source = "numberOfSeats", target = "seatCount")
	CarDto carToCarDto(Car car);
}
