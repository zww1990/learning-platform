package io.example.statemachine.mapper;

import io.example.statemachine.domain.Order;
import org.apache.ibatis.annotations.*;

/**
 * 订单数据访问对象接口
 *
 * @author 张维维
 * @since 2023-09-29 18:28:03
 */
@Mapper
public interface OrderMapper {
    @Select("select * from tb_order where id = #{id}")
    Order selectById(@Param("id") Long id);

    @Insert("""
                insert into tb_order (order_code, status, price, create_user, update_user, create_time, update_time)
                values (#{orderCode}, #{status}, #{price}, #{createUser}, #{updateUser}, #{createTime}, #{updateTime})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Order order);

    @Update("update tb_order set status = #{status}, update_user = #{updateUser}, update_time = #{updateTime} where id = #{id}")
    int updateById(Order order);
}
