package com.laoayu.parking.system.controller;

import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.Menu;
import com.laoayu.parking.system.service.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器R
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
@Api(tags = "用户菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation("查询所有菜单数据")
    @GetMapping
    public Result<?> getAllMenu() {

        List<Menu> menuList = menuService.getAllMenu();
        return Result.success(menuList);
    }

}
