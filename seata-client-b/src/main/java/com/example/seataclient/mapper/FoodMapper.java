package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.seataclient.domain.Food;

@Mapper
public interface FoodMapper {
	@Insert("insert into food (food_name, stock) values (#{foodName}, #{stock})")
	int insert(Food food);

	@Update("update food set food_name=#{foodName}, stock=#{stock} where food_id=#{foodId}")
	int update(Food food);

	@Select("select * from food where food_id=#{foodId}")
	Food selectByFoodId(@Param("foodId") Integer foodId);
}
