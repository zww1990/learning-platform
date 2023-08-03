package com.demo.mapper;

import com.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author javaer
 * @since 2023-08-03 10:31:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
