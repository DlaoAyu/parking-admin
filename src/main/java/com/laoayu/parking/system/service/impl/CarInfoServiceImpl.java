package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.system.entity.CarInfo;
import com.laoayu.parking.system.entity.CarScan;
import com.laoayu.parking.system.entity.ParkCar;
import com.laoayu.parking.system.mapper.CarInfoMapper;
import com.laoayu.parking.system.mapper.ParkCarMapper;
import com.laoayu.parking.system.service.ICarInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 固定车信息 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */
@Service
public class CarInfoServiceImpl extends ServiceImpl<CarInfoMapper, CarInfo> implements ICarInfoService {

    @Resource
    private ParkCarMapper parkCarMapper;

    @Resource
    private CarInfoMapper carInfoMapper;

    @Override
    @Transactional
    public void addCarInfo(CarInfo carInfo) {
        //写入固定车表
        this.baseMapper.insert(carInfo);

        //写入固定车对应停车场表
        List<Long> parkIdList = carInfo.getParkIdList();
        if (parkIdList != null) {
            for (Long parkId : parkIdList) {
                parkCarMapper.insert(new ParkCar(carInfo.getCarId(),parkId));
            }
        }
    }

    @Override
    @Transactional
    public void updateCarInfo(CarInfo carInfo) {
        //更新固定车信息表
        this.baseMapper.updateById(carInfo);

        //删除原有固定车对应停车场数据
        LambdaQueryWrapper<ParkCar> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkCar::getCarId,carInfo.getCarId());
        parkCarMapper.delete(wrapper);

        //重新写入固定车对应停车场
        List<Long> parkIdList = carInfo.getParkIdList();
        if (parkIdList != null) {
            for (Long parkId : parkIdList) {
                parkCarMapper.insert(new ParkCar(carInfo.getCarId(),parkId));
            }
        }


    }

    @Override
    public CarInfo getCarInfoById(Long carId) {

        //根据固定车Id查数据
        CarInfo carInfo = this.baseMapper.selectById(carId);

        //查固定车对应停车场
        LambdaQueryWrapper<ParkCar> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkCar::getCarId,carId);
        List<ParkCar> parkCarList = parkCarMapper.selectList(wrapper);

        List<Long> parkIdList = parkCarList.stream()
                .map(parkCar -> {return parkCar.getParkId();})
                .collect(Collectors.toList());

        carInfo.setParkIdList(parkIdList);

        return carInfo;

    }

    @Override
    @Transactional
    public void deleteCarInfoById(Long carId) {
        //删除固定车信息
        this.baseMapper.deleteById(carId);

        //删除对应停车场中的固定车
        LambdaQueryWrapper<ParkCar> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ParkCar::getCarId,carId);

        parkCarMapper.delete(wrapper);

    }

    @Override
    public Page<CarInfo> getCarInfoList(Page<CarInfo> page, String belongName, String plateColor, String plateNum, String userName) {
        return carInfoMapper.getCarInfoList(page, belongName, plateColor, plateNum, userName);
    }

    @Override
    public CarInfo getByPlateNumber(String plateNum, Long parkId) {

        return carInfoMapper.getByPlateNumber(plateNum,parkId);
    }
}
