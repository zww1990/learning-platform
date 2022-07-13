package com.example.seataclient.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.seataclient.domain.Food;

/**
 * 食品持久化接口
 * 
 * @author zww1990@foxmail.com
 * @since 2021年12月19日,下午5:04:22
 */
@Mapper
public interface FoodMapper {
	/**
	 * 插入记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:04:35
	 * @param food 食品
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "foodId")
	@Insert("insert into food (food_name, stock) values (#{foodName}, #{stock})")
	int insert(Food food);

	/**
	 * 更新记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:04:38
	 * @param food 食品
	 * @return
	 */
	@Update("update food set food_name=#{foodName}, stock=stock - #{stock} where food_id=#{foodId}")
	int update(Food food);

	/**
	 * 按主键查询单条记录
	 * 
	 * @author zww1990@foxmail.com
	 * @since 2021年12月19日,下午5:04:42
	 * @param foodId 主键
	 * @return
	 */
	@Select("select * from food where food_id=#{foodId}")
	Food selectByFoodId(@Param("foodId") Integer foodId);
}
