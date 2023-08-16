package com.laoayu.parking.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.ParkOrder;
import com.laoayu.parking.system.service.ICarInfoService;
import com.laoayu.parking.system.service.IParkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 订单信息表 前端控制器
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */

@Api(tags = {"车辆订单记录list"})
@RestController
@RequestMapping("/parkOrder")
public class ParkOrderController {

    @Autowired
    private IParkOrderService parkOrderService;

    @Resource
    private ICarInfoService carInfoService;

    @ApiOperation("查询根据条件查对应停车场订单，根据登录的用户名查对应List")
    @GetMapping("/getParkOrderList")
    public Result<Map<String, Object>> getParkOrderList(@RequestParam(value = "plateColor",required = false) String plateColor,
                                                        @RequestParam(value = "type",required = false) String type,
                                                        @RequestParam(value = "parkName",required = false) String parkName,
                                                        @RequestParam(value = "userName",required = false) String userName,
                                                        @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                        @RequestParam(value = "pageSize",required = false) Long pageSize){

        Page<ParkOrder> page = parkOrderService.getParkOrderList(new Page<>(pageNum,pageSize),plateColor,type,parkName,userName);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    //删除订单
    @ApiOperation("删除订单")
    @DeleteMapping("/{id}")
    public Result<ParkOrder> deleteParkOrderById(@PathVariable("id") Long id) {
        parkOrderService.deleteParkOrderById(id);
        return Result.success("成功删除这条订单");
    }

    //查询对应管理的停车场的总收入数
    @ApiOperation("计算总收入")
    @GetMapping("/totalIncome")
    public Result<BigDecimal> getTotalIncome(@RequestParam(value = "userName", required = false) String userName) {

        // 计算订单的总收入
        BigDecimal totalIncome = parkOrderService.getTotalIncome(userName);

        return Result.success(totalIncome);
    }
}