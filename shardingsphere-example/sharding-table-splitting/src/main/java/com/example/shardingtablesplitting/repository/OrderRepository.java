package com.example.shardingtablesplitting.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.shardingtablesplitting.domain.Order;

@Mapper
public interface OrderRepository {

	@Insert("""
			INSERT INTO t_order (user_id, product_name, quantity)
			 VALUES (#{userId}, #{productName}, #{quantity})
			""")
	int insertOrder(Order order);

	@Select("select * from t_order order by order_id")
	List<Order> selectOrders();

	@Select("""
			<script>
			select * from t_order where user_id in
			<foreach collection="userIds" item="id" open="(" separator="," close=")">#{id}</foreach>
			</script>
			""")
	List<Order> selectOrdersByUserIds(@Param("userIds") List<Integer> userIds);
}
