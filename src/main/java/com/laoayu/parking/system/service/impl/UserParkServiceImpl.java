package com.laoayu.parking.system.service.impl;

import com.laoayu.parking.system.entity.UserPark;
import com.laoayu.parking.system.mapper.UserParkMapper;
import com.laoayu.parking.system.service.IUserParkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户停车场关联表，用于连接管理员所管理的停车场 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-28
 */
@Service
public class UserParkServiceImpl extends ServiceImpl<UserParkMapper, UserPark> implements IUserParkService {

}
