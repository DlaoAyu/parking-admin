package com.laoayu.parking.system.service.impl;

import com.laoayu.parking.system.entity.ParkCar;
import com.laoayu.parking.system.mapper.ParkCarMapper;
import com.laoayu.parking.system.service.IParkCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 固定车和停车场关联表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-23
 */
@Service
public class ParkCarServiceImpl extends ServiceImpl<ParkCarMapper, ParkCar> implements IParkCarService {

}
