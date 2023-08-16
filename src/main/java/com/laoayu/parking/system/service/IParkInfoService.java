package com.laoayu.parking.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.ParkInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 停车场信息表 服务类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
public interface IParkInfoService extends IService<ParkInfo> {

    /**
     * 添加停车场信息
     * @param parkInfo
     */
    void addParkInfo(ParkInfo parkInfo);

    /**
     * 修改停车场信息
     * @param parkInfo
     */
    void updateParkInfo(ParkInfo parkInfo);

    /**
     * 根据parkId查停车场信息
     * @param parkId
     * @return
     */
    ParkInfo getParkInfoById(Long parkId);

    /**
     * 删除停车场信息
     * @param parkId
     */
    void deleteParkInfoById(Long parkId);

    /**
     * 根据登录的用户查停车场List
     * @param userId
     * @return
     */
    List<ParkInfo> getParkInfoByUserId(Long userId);

    /**
     * 根据parkId查询停车场信息
     * @param parkId
     * @return
     */
    ParkInfo selectParkInfoByParkId(Long parkId);

    /**
     * 根据用户查总停车位和剩余停车位数量
     * @param userName
     * @return
     */
    Map<String, Object> getParkSpace(String userName);

    /**
     * 根据登录用户查停车场列表
     * @param page
     * @param parkName
     * @param parkAddress
     * @param userName
     * @return
     */
    Page<ParkInfo> getParkInfoList(Page<Object> page, String parkName, String parkAddress, String userName);
}
