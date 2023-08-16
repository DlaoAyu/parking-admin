package com.laoayu.parking.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.CarInfo;
import com.laoayu.parking.system.entity.ParkInfo;
import com.laoayu.parking.system.service.IParkInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 停车场信息表 前端控制器
 * </p>
 *
 * @author laoayu
 * @since 2023-03-18
 */

@Api(tags = {"停车场管理接口列表"})
@RestController
@RequestMapping("/parkInfo")
public class ParkInfoController {

    @Autowired
    private IParkInfoService parkInfoService;

    @ApiOperation("查询所有停车场接口")
    @GetMapping("/all")
    public Result<List<ParkInfo>> getAllParkInfoList(){
        List<ParkInfo> parkInfoList = parkInfoService.list();
        return Result.success(parkInfoList,"查询成功");
    }

    @ApiOperation("查询所有停车场接口")
    @GetMapping("/getParkInfoList")
    public Result<Map<String, Object>> getParkInfoList(@RequestParam(value = "parkName",required = false) String parkName,
                                                       @RequestParam(value = "parkAddress",required = false) String parkAddress,
                                                       @RequestParam(value = "userName",required = false) String userName,
                                                       @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                       @RequestParam(value = "pageSize",required = false) Long pageSize){
//        LambdaQueryWrapper<ParkInfo> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StringUtils.hasLength(parkName),ParkInfo::getParkName,parkName);
//        wrapper.like(StringUtils.hasLength(parkAddress),ParkInfo::getParkAddress,parkAddress);
//
//        Page<ParkInfo> page = new Page<>(pageNum,pageSize);
//        parkInfoService.page(page,wrapper);
        Page<ParkInfo> page = parkInfoService.getParkInfoList(new Page<>(pageNum,pageSize),parkName,parkAddress,userName);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);
    }

    //新增停车场
    @ApiOperation("新增停车场接口")
    @PostMapping
    public Result<?> addParkInfo(@RequestBody ParkInfo parkInfo) {

        parkInfoService.addParkInfo(parkInfo);

        return Result.success("新增停车场成功");
    }

    //修改角色停车场
    @ApiOperation("修改停车场信息接口")
    @PutMapping
    public Result<?> updateParkInfo(@RequestBody ParkInfo parkInfo) {

        parkInfoService.updateParkInfo(parkInfo);
        return Result.success("停车场信息修改成功");
    }

    //根据parkId查询
    @ApiOperation("根据parkId查询角色接口")
    @GetMapping("/{parkId}")
    public Result<ParkInfo> getParkInfoById(@PathVariable("parkId") Long parkId) {
        ParkInfo parkInfo = parkInfoService.getParkInfoById(parkId);
        return Result.success(parkInfo);
    }

    //删除角色（逻辑删除）
    @ApiOperation("删除停车场接口")
    @DeleteMapping("/{parkId}")
    public Result<ParkInfo> deleteParkInfoById(@PathVariable("parkId") Long parkId) {
        parkInfoService.deleteParkInfoById(parkId);
        return Result.success("成功删除角色");
    }

    //查询总车位数和剩余车位数
    @ApiOperation("查询总车位数和剩余车位数")
    @GetMapping("/parkSpace")
    public Result<Map<String, Object>> getParkSpace(@RequestParam(value = "userName", required = false) String userName) {

        Map<String, Object> data = parkInfoService.getParkSpace(userName);

        return Result.success(data);
    }


}
