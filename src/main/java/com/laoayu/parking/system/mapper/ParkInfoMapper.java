package com.laoayu.parking.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.ParkInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 停车场信息表 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
public interface ParkInfoMapper extends BaseMapper<ParkInfo> {
    public List<ParkInfo> getParkInfoByUserId (Long userId);

    List<ParkInfo> selectParkInfoByParkId(Long parkId);

    @SuppressWarnings("MybatisXMapperMethodInspection")//解决@MapKey is required 报错问题
    Map<String, Object> getParkSpace(String userName);

    Page<ParkInfo> getParkInfoList(Page<Object> page, String parkName, String parkAddress, String userName);
}
