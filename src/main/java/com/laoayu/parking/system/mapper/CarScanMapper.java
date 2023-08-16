package com.laoayu.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.CarScan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 车辆出入信息表 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-04-06
 */
public interface CarScanMapper extends BaseMapper<CarScan> {

    Page<CarScan> getCarScanList(Page<CarScan> page, String plateColor, String type, String direction, String userName);
}
