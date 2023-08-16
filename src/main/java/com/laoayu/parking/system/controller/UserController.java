package com.laoayu.parking.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laoayu.parking.common.vo.Result;
import com.laoayu.parking.system.entity.User;
import com.laoayu.parking.system.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */

@Api(tags = {"用户接口列表"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("查询所有用户接口")
    @GetMapping("/all")
    public Result<List<User>> getAllUser(){
        List<User> list = userService.list();
        return Result.success(list,"查询成功");
    }

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> data = userService.login(user);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(202,"用户名或密码错误");
    }

    @ApiOperation("获取用户信息接口")
    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息，redis
        Map<String,Object> data = userService.getUserInfo(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(203,"登录信息无效，请重新登录");
    }

    @ApiOperation("退出登录接口")
    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("P-Token") String token){
        userService.logout(token);
        return Result.success();
    }

    @ApiOperation("获取用户列表接口")
    @GetMapping("/getUserList")
    public Result<Map<String,Object>> getUserList(@RequestParam(value = "userName",required = false) String userName,
                                                  @RequestParam(value = "phonenumber",required = false) String phonenumber,
                                                  @RequestParam(value = "sex",required = false) String sex,
                                                  @RequestParam(value = "pageNum",required = false) Long pageNum,
                                                  @RequestParam(value = "pageSize",required = false) Long pageSize){

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(userName),User::getUserName,userName);
        wrapper.eq(StringUtils.hasLength(phonenumber),User::getPhonenumber,phonenumber);
        wrapper.eq(StringUtils.hasLength(sex),User::getSex,sex);

        Page<User> page = new Page<>(pageNum,pageSize);
        userService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());

        return Result.success(data);

    }

    //新增用户
    @ApiOperation("新增用户接口")
    @PostMapping
    public Result<?> addUser(@RequestBody User user) {

        User user1 = selectUserByUserName(user.getUserName());
        if (user1 == null){
            //密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userService.addUser(user);
            return Result.success("新增用户成功");
        } else{
            return Result.fail("该用户名已被使用，请重新输入");
        }

//        //密码加密
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userService.addUser(user);
//        return Result.success("新增用户成功");
    }

    private User selectUserByUserName(String userName) {

        User user = userService.selectUserByUserName(userName);

        return user;
    }

    //用户修改
    @ApiOperation("修改用户信息接口")
    @PutMapping
    public Result<?> updateUser(@RequestBody User user) {
        user.setPassword(null);

        userService.updateUser(user);
        return Result.success("用户信息修改成功");
    }

    //根据userId查询
    @ApiOperation("根据userId查询用户接口")
    @GetMapping("/{userId}")
    public Result<User> getUserById(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        return Result.success(user);
    }

    //删除用户（逻辑删除）
    @ApiOperation("删除用户接口")
    @DeleteMapping("/{userId}")
    public Result<User> deleteUserById(@PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
        return Result.success("成功删除用户");
    }
}
