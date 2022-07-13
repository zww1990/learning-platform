package com.example.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.test.model.Person;

@Mapper
public interface PersonMapper {
	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	Person copyPerson(Person person);
}
