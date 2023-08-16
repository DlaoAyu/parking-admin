package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.ParkOrder;
import com.laoayu.parking.system.mapper.ParkOrderMapper;
import com.laoayu.parking.system.service.IParkOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 订单信息表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
@Service
public class ParkOrderServiceImpl extends ServiceImpl<ParkOrderMapper, ParkOrder> implements IParkOrderService {

    @Resource
    private ParkOrderMapper parkOrderMapper;

    @Override
    public Page<ParkOrder> getParkOrderList(Page<ParkOrder> page, String plateColor, String type, String parkName, String userName) {
        return parkOrderMapper.getParkOrderList(page,plateColor,type,parkName,userName);
    }

    /**
     * 根据 车牌号和停车场id查询车辆是否入库
     * @param plateNum
     * @param parkId
     * @return
     */
    @Override
    public ParkOrder getByPlateNumber(String plateNum, Long parkId) {
        return parkOrderMapper.getByPlateNumber(plateNum,parkId);
    }

    @Override
    public ParkOrder selectParkOrderByParkIdAndPlateNum(Long parkId, String plateNum) {
        List<ParkOrder> parkOrders = parkOrderMapper.selectParkOrderByParkIdAndPlateNum(parkId,plateNum);
        if (parkOrders.isEmpty() || parkOrders.size() == 0){
//            throw  new RuntimeException("未找到停车订单");
            return null;
        }
        ParkOrder parkOrder = new ParkOrder();
        for (ParkOrder carEntry1 : parkOrders) {
            if (carEntry1.getExitTime() == null ){
                parkOrder = carEntry1;
            }
        }
        return parkOrder;
    }

    @Override
    public void deleteParkOrderById(Long id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 计算总收入
     * @param userName
     * @return
     */
    @Override
    public BigDecimal getTotalIncome(String userName) {
        return parkOrderMapper.getTotalIncome(userName);
    }
}
