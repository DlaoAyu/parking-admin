package com.laoayu.parking.system.controller;

import ch.qos.logback.core.util.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.utils.ApiOcrUtil;
import com.laoayu.parking.common.utils.DateUtil;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.common.vo.TimeVo;
import com.laoayu.parking.system.entity.*;
import com.laoayu.parking.system.mapper.ParkInfoMapper;
import com.laoayu.parking.system.mapper.ParkOrderMapper;
import com.laoayu.parking.system.service.ICarInfoService;
import com.laoayu.parking.system.service.ICarScanService;
import com.laoayu.parking.system.service.IParkInfoService;
import com.laoayu.parking.system.service.IParkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 车辆出入信息表 前端控制器
 * </p>
 *
 * @author laoayu
 * @since 2023-04-06
 */

@Api(tags = {"车辆出入场扫描记录"})
@RestController
@RequestMapping("/carScan")
public class CarScanController {

    @Autowired
    private ApiOcrUtil apiOcrUtil;

    @Autowired
    private ICarScanService scanService;

    @Autowired
    private IParkInfoService parkInfoService;

    @Autowired
    private IParkOrderService parkOrderService;

    @Resource
    private ParkOrderMapper parkOrderMapper;

    @Resource
    private ParkInfoMapper parkInfoMapper;

    @Resource
    private ICarInfoService carInfoService;

    @ApiOperation("查询根据条件查对应停车场车辆扫描结果，根据登录的用户名查对应List")
    @GetMapping("/getCarScanList")
    public Result<Map<String, Object>> getCarScanList(@RequestParam(value = "plateColor",required = false) String plateColor,
                                                      @RequestParam(value = "type",required = false) String type,
                                                      @RequestParam(value = "direction",required = false) String direction,
                                                      @RequestParam(value = "userName",required = false) String userName,
                                                      @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                      @RequestParam(value = "pageSize",required = false) Long pageSize){

        Page<CarScan> page = scanService.getCarScanList(new Page<>(pageNum,pageSize),plateColor,type,direction,userName);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    //删除进出记录
    @ApiOperation("删除进出记录")
    @DeleteMapping("/{id}")
    public Result<CarScan> deleteCarScanById(@PathVariable("id") Long id) {
        scanService.deleteCarScanById(id);
        return Result.success("成功删除记录");
    }


    /**
     * 该方法实现，传入图片返回车辆信息，返回结果示例
     * {
     *     "code": 200,
     *     "message": "success",
     *     "data": {
     *         "imagePath": "http://localhost:9999/pic/941d622cea68410eaf9b09a039526843test1.jpg",
     *         "scanTime": "2023-04-11 14:18:12",
     *         "plate": {
     *             "number": "鲁B995EQ",
     *             "color": "blue"
     *         }
     *     }
     * }
     * @param file
     * @param parkId
     * @return
     */
    @ApiOperation("扫描车牌得到结果")
    @RequestMapping("/carScan")
    private Result<Map<String, Object>> carScan(MultipartFile[] file, Long parkId){

        StringBuffer sb = new StringBuffer();
        // 出入场车牌识别图片存储地址
        String filePath = "D:\\ParkingManagementSystem\\Parking\\car_pic";
        File fileDir = new File(filePath);
        try {
            // 不传图片，此处捕获空指针异常，代码照常运行
            for(int x = 0; x<file.length; x++){
                MultipartFile uploadFile = file[x];
                // 存储图片
                File uploadDir = new File(fileDir.getAbsolutePath() + File.separator);
                if ( uploadFile != null) {
                    //获得上传文件的文件名
                    String oldName = uploadFile.getOriginalFilename();
                    System.out.println("[上传的文件名]：" + oldName);
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    String newName = uuid + oldName;
                    System.out.println("[新文件名]：" + newName);
                    if (sb.toString().equals("")){
                        sb.append("http://localhost:9999/pic/"+newName);
                        System.out.println("[图片访问路径]：" + sb.toString());
                    }else {
                        sb.append("|" + newName);
                    }
                    String imagePath = sb.toString();
                    //System.out.println("访问路径："+imagePath);
                    // pic 为文件在磁盘中存储的位置
                    File pic = new File(uploadDir, newName);
                    try {
                        //保存图片
                        uploadFile.transferTo(pic);
                        //返回成功结果，附带文件的相对路径
                        // plateLicense传入的应该为文件而非路径
                        Map plate= apiOcrUtil.plateLicense(pic.getAbsolutePath());
                        // 判断 plate这个map中是否为空
                        if(plate.isEmpty()){
                            return Result.fail("识别失败");
                        }
                        //获取当前时间
                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String scanTime = sdf.format(System.currentTimeMillis());
                        //将扫描结果存入map中作为结果返回
                        Map<String, Object> map = new HashMap<>();
                        map.put("plate",plate);
                        map.put("imagePath",imagePath);
                        map.put("scanTime",scanTime);

                        System.out.println("扫描时间：" + scanTime);
                        System.out.println("车牌信息map：" + map);
                        System.out.println("车牌信息：" + plate);
                        System.out.println("实际路径："+pic);

                        return Result.success(map);
                    }catch (IOException e) {
                        System.out.println("=====捕获异常1=====");
                        e.printStackTrace();
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println("=====捕获异常2=====");
            e.printStackTrace();
        }
        return null;
    }

    // 车辆入场
    @ApiOperation("车辆入场")
    @PostMapping("/addEntryCar")
    public Result<?> addEntryCar(@RequestBody CarScan carScan) {

        //查询未完成订单
        ParkOrder parkOrder = selectParkOrderByParkIdAndPlateNum(carScan.getParkId(),carScan.getPlateNum());

        //查询停车场
        ParkInfo parkInfo = selectParkInfoByParkId(carScan.getParkId());

        if (parkOrder == null && parkInfo.getParkSpare() > 0) {
            //将入场信息写入出场场表中
            scanService.addEntryCar(carScan);

            ParkInfo parkInfo1 = new ParkInfo();
            parkInfo1.setParkSpare(parkInfo.getParkSpare()-1);//剩余车位数
            parkInfo1.setParkId(parkInfo.getParkId());

            parkInfoMapper.updateById(parkInfo1);

        } else if (parkOrder != null){

            return Result.fail("车辆已入库请勿重复扫描");

        } else if (parkInfo.getParkSpare() <=0){

            return Result.fail("车位不足,请换个停车场吧");

        }


//        //修改停车剩余车位数
//        if (parkInfo.getParkSpare() >0) {
//            ParkInfo parkInfo1 = new ParkInfo();
//            parkInfo1.setParkSpare(parkInfo.getParkSpare()-1);//剩余车位数
//            parkInfo1.setParkId(parkInfo.getParkId());
//
//            parkInfoMapper.updateById(parkInfo1);
//        } else {
//            return Result.fail("车位不足,请换个停车场吧");
//        }
        return Result.success("车辆进场成功");
    }

    // 车辆出场
    @ApiOperation("车辆出场")
    @PostMapping("/exitCar")
    public Result<?> exitCar(@RequestBody CarScan carScan) throws ParseException {

//        //将出场信息填入出入场表中
//        scanService.exitCar(carScan);

        //查询停车场
        ParkInfo parkInfo = selectParkInfoByParkId(carScan.getParkId());

        //查询未完成订单
        ParkOrder parkOrder = selectParkOrderByParkIdAndPlateNum(carScan.getParkId(),carScan.getPlateNum());

        //查询固定车查询后的结果用于判断固定车时间是否过期
        CarInfo carInfo = selectCarInfoByParkIdAndPlateNum(carScan.getParkId(),carScan.getPlateNum());

        /**
         * 计算停车时间和需要缴费的金额！！
         *
         */
        if(parkOrder != null) {
            //入场时间查询
            ParkOrder order = new ParkOrder();
            order.setParkId(parkInfo.getParkId());  //停车场Id
            order.setEntryTime(parkOrder.getEntryTime());   //入场时间
            order.setPlateNum(carScan.getPlateNum());   //车牌号
            order.setPlateColor(carScan.getPlateColor());   //车牌颜色
            order.setParkRule(parkInfo.getParkRule());  //收费规则
            order.setId(parkOrder.getId()); //订单Id
            order.setExitTime(carScan.getExitTime());   //出场时间
            order.setType(parkOrder.getType()); //是否为固定车

            System.out.println("出场时间"+carScan.getExitTime());

            //剩余车位数加1
            ParkInfo parkInfo1 = new ParkInfo();
            parkInfo1.setParkSpare(parkInfo.getParkSpare()+1);//剩余车位数
            parkInfo1.setParkId(parkInfo.getParkId());
            try {
                TimeVo time = DateUtil.time(parkOrder.getEntryTime(), carScan.getExitTime());//时间差
                Long min = 0l;
                min = time.getDay() * 24 * 60 + time.getHour() * 60 + time.getMin();
                int hours = Math.toIntExact(min / 60); // 计算总分钟数包含多少个完整小时
                if (min % 60 != 0) { // 判断是否存在不足一小时的部分
                    hours++; // 不足一小时按照一小时计算
                }
                // 车辆是固定车
                if (parkOrder.getType() == 1) {
                    //出场时间
                    Calendar date = Calendar.getInstance();
                    date.setTime(carScan.getExitTime());
                    System.out.println("出场时间" + date);
                    //开始日期
                    Calendar beginTime = Calendar.getInstance();
                    beginTime.setTime(carInfo.getBeginTime());
                    System.out.println("开始日期" + beginTime);
                    //到期日期
                    Calendar endTime = Calendar.getInstance();
                    endTime.setTime(carInfo.getEndTime());
                    System.out.println("到期日期" + endTime);
                    //是否到期
        //                int dueOrNot;
        //                if (date.after(beginTime) && date.before(endTime)) {
        //                    dueOrNot = 1;
        //                    System.out.println("未到期" +dueOrNot);
        //                } else {
        //                    dueOrNot = 0;
        //                    System.out.println("已到期" + dueOrNot);
        //                }
        //                System.out.println("是否过期：" + dueOrNot);

                    // 判断目标日期是否在开始日期和结束日期之间（是否到期）
                    boolean isInDateTimeRange = date.after(beginTime) && date.before(endTime);

                    // 是固定车且未到期
                    if (isInDateTimeRange) {
                        //将出场信息填入出入场表中
                        scanService.exitCar(carScan);
                        // 是固定车且未到期
                        if (min <= 60) {
                            order.setParkingDuration(1);
                            order.setParkFee(BigDecimal.valueOf(0));
                            parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                            parkOrderMapper.updateById(order);//修改订单表
                            return Result.success(order, "固 定 车 ！");
                        } else if (min > 60) {
                            //order.setParkingDuration(Math.toIntExact(min / 60));
                            order.setParkingDuration(hours);
                            order.setParkFee(BigDecimal.valueOf(0));
                            parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                            parkOrderMapper.updateById(order);//修改订单表
                            return Result.success(order, "固 定 车 ！");
                        }
                    } else {
                        //将出场信息填入出入场表中
                        scanService.exitCar(carScan);
                        // 是固定车但是已过期
                        if (min <= 60) {
                            order.setParkingDuration(1);
                            order.setParkFee(order.getParkRule());
                            parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                            parkOrderMapper.updateById(order);//修改订单表
                            return Result.success(order, "固定车已过期！");
                        } else if (min > 60) {
//                            order.setParkingDuration(Math.toIntExact(min / 60));
                            order.setParkingDuration(hours);
                            System.out.println("计算多少分钟" + min +"\n计算多少小时，不满一小时按一小时计算" + hours);
//                            order.setParkFee(order.getParkRule().multiply(BigDecimal.valueOf(min / 60)));
                            order.setParkFee(order.getParkRule().multiply(BigDecimal.valueOf(hours)));
                            if (order.getParkFee().compareTo(BigDecimal.valueOf(1000)) > -1) {
                                order.setParkFee(BigDecimal.valueOf(1000));
                            }
                            parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                            parkOrderMapper.updateById(order);//修改订单表
                            //System.out.println("订单" + order);
                            return Result.success(order, "固定车已过期！");
                        }
                    }
                } else if (parkOrder.getType() == 0) {
                    //将出场信息填入出入场表中
                    scanService.exitCar(carScan);
                    if (min <= 60) {
                        order.setParkingDuration(1);
                        order.setParkFee(order.getParkRule());
                        parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                        parkOrderMapper.updateById(order);//修改订单表
                        return Result.success(order, "临 时 车 ！");
                    } else if (min > 60) {
//                        order.setParkingDuration(Math.toIntExact(min / 60));
                        order.setParkingDuration(hours);
                        System.out.println("计算多少分钟" + min +"\n计算多少小时，不满一小时按一小时计算" + hours);
//                        order.setParkFee(order.getParkRule().multiply(BigDecimal.valueOf(min / 60)));
                        order.setParkFee(order.getParkRule().multiply(BigDecimal.valueOf(hours)));
                        if (order.getParkFee().compareTo(BigDecimal.valueOf(1000)) > -1) {
                            order.setParkFee(BigDecimal.valueOf(1000));
                        }
                        parkInfoMapper.updateById(parkInfo1);   //剩余车位数加一
                        parkOrderMapper.updateById(order);//修改订单表
                        //System.out.println("订单" + order);
                        return Result.success(order, "临 时 车 ！");
                    }
                }
            } catch (ParseException e) {
                System.out.println("出场异常！！！");
                e.printStackTrace();
            }
        } else {
            return Result.fail("车辆未入库，无法出库");
        }
        return null;
    }

    /**
     * 根据 parkId plateNum 查询固定车免费时间
     * @param parkId
     * @param plateNum
     * @return
     */
    private CarInfo selectCarInfoByParkIdAndPlateNum(Long parkId, String plateNum) {

        CarInfo carInfo = carInfoService.getByPlateNumber(plateNum,parkId);
        return carInfo;
    }

    /**
     * 根据 parkId plateNum 查询未完成订单
     * @param parkId
     * @param plateNum
     * @return
     */
    private ParkOrder selectParkOrderByParkIdAndPlateNum(Long parkId, String plateNum) {

        ParkOrder parkOrder = parkOrderService.selectParkOrderByParkIdAndPlateNum(parkId,plateNum);
        return parkOrder;
    }

    /**
     * 根据parkId查询停车场信息
     * @param parkId
     * @return
     */
    private ParkInfo selectParkInfoByParkId(Long parkId) {
        ParkInfo parkInfo = parkInfoService.selectParkInfoByParkId(parkId);
        return parkInfo;
    }


}
