package com.laoayu.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.CarInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 固定车信息 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
public interface CarInfoMapper extends BaseMapper<CarInfo> {

    Page<CarInfo> getCarInfoList(Page<CarInfo> page, String belongName, String plateColor, String plateNum, String userName);

    CarInfo getByPlateNumber(String plateNum, Long parkId);
}
