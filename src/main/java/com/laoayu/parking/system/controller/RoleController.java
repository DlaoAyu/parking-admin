package com.laoayu.parking.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.Role;
import com.laoayu.parking.system.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */

@Api(tags = {"角色接口列表"})
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @ApiOperation("获取角色列表接口")
    @GetMapping("/getRoleList")
    public Result<Map<String,Object>> getRoleList(@RequestParam(value = "roleName",required = false) String roleName,
                                                  @RequestParam(value = "flag",required = false) String flag,
                                                  @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                  @RequestParam(value = "pageSize",required = false) Long pageSize){

        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(roleName),Role::getRoleName,roleName);
        wrapper.like(StringUtils.hasLength(flag),Role::getFlag,flag);

        Page<Role> page = new Page<>(pageNum,pageSize);
        roleService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);

    }

    //新增角色
    @ApiOperation("新增角色接口")
    @PostMapping
    public Result<?> addRole(@RequestBody Role role) {

        roleService.addRole(role);
        return Result.success("新增角色成功");
    }

    //角色修改
    @ApiOperation("修改角色信息接口")
    @PutMapping
    public Result<?> updateRole(@RequestBody Role role) {

        roleService.updateRole(role);
        return Result.success("角色信息修改成功");
    }

    //根据roleId查询
    @ApiOperation("根据roleId查询角色接口")
    @GetMapping("/{roleId}")
    public Result<Role> getRoleById(@PathVariable("roleId") Long roleId) {
        Role role = roleService.getRoleById(roleId);
        return Result.success(role);
    }

    //删除角色（逻辑删除）
    @ApiOperation("删除角色接口")
    @DeleteMapping("/{roleId}")
    public Result<Role> deleteRoleById(@PathVariable("roleId") Long roleId) {
        roleService.deleteRoleById(roleId);
        return Result.success("成功删除角色");
    }

    //查询所有角色
    @ApiOperation("查询所有角色接口")
    @GetMapping("/all")
    private Result<List<Role>> getAllRole(){
            List<Role> roleList = roleService.list();
            return Result.success(roleList);
    }
}
