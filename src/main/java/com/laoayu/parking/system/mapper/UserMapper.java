package com.laoayu.parking.system.mapper;

import com.laoayu.parking.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
public interface UserMapper extends BaseMapper<User> {

    public List<String> getRoleNameByUserId(Long userId);

    User selectUserByUserName(String userName);
}
