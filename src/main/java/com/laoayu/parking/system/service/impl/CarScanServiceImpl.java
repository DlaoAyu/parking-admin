package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.CarInfo;
import com.laoayu.parking.system.entity.CarScan;
import com.laoayu.parking.system.entity.ParkOrder;
import com.laoayu.parking.system.mapper.CarScanMapper;
import com.laoayu.parking.system.mapper.ParkOrderMapper;
import com.laoayu.parking.system.service.ICarInfoService;
import com.laoayu.parking.system.service.ICarScanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laoayu.parking.system.service.IParkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 车辆出入信息表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-04-06
 */
@Service
public class CarScanServiceImpl extends ServiceImpl<CarScanMapper, CarScan> implements ICarScanService {

    @Resource
    private CarScanMapper carScanMapper;

    @Resource
    private ICarInfoService carInfoService;

    @Resource
    private ParkOrderMapper parkOrderMapper;

    @Autowired
    private IParkOrderService parkOrderService;


    /**
     * 根据userId,和前端输入框内容查询扫描结果List
     * @param page
     * @param plateColor
     * @param type
     * @param direction
     * @param userName
     * @return
     */
    @Override
    public Page<CarScan> getCarScanList(Page<CarScan> page, String plateColor, String type, String direction, String userName) {
        return carScanMapper.getCarScanList(page,plateColor,type,direction,userName);
    }

    @Override
    public void addEntryCar(CarScan carScan) {

        Long parkId = carScan.getParkId();
        String plateNum = carScan.getPlateNum();

        //根据车牌号和停车场号判断是否为固定车
        CarInfo carInfo = carInfoService.getByPlateNumber(plateNum,parkId);
        if (carInfo != null){
            carScan.setType(1);//固定车
        }else {
            carScan.setType(0);//临时车
        }
        carScan.setDirection(0);//入场

        //写入出入场信息表
        this.baseMapper.insert(carScan);


        //写入订单表
        ParkOrder parkOrder = new ParkOrder();
        parkOrder.setEntryTime(carScan.getCreateTime());    //入场时间
        parkOrder.setParkId(carScan.getParkId());   //停车场Id
        parkOrder.setType(carScan.getType());   //是否为固定车
        parkOrder.setPlateColor(carScan.getPlateColor());   //车牌颜色
        parkOrder.setPlateNum(carScan.getPlateNum());   //车牌号

        parkOrderMapper.insert(parkOrder);

//        parkOrderMapper.insert(new ParkOrder(null,null,carScan.getParkId(),carScan.getType(),
//                carScan.getPlateColor(),carScan.getPlateNum(),null,carScan.getEntryTime(),
//                null,null,null,null,null));

    }

    @Override
    public void exitCar(CarScan carScan) {
        Long parkId = carScan.getParkId();
        String plateNum = carScan.getPlateNum();

        //根据车牌号和停车场号判断是否为固定车
        CarInfo carInfo = carInfoService.getByPlateNumber(plateNum,parkId);
        if (carInfo != null){
            carScan.setType(1);//固定车
        }else {
            carScan.setType(0);//临时车
        }
        carScan.setDirection(1);//出场

        //写入出入场信息表
        this.baseMapper.insert(carScan);
    }

    @Override
    public void deleteCarScanById(Long id) {
        this.baseMapper.deleteById(id);
    }
}
