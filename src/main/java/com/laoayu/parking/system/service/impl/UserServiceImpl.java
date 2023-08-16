package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laoayu.parking.common.utils.JwtUtil;
import com.laoayu.parking.system.entity.*;
import com.laoayu.parking.system.mapper.UserMapper;
import com.laoayu.parking.system.mapper.UserParkMapper;
import com.laoayu.parking.system.mapper.UserRoleMapper;
import com.laoayu.parking.system.service.IMenuService;
import com.laoayu.parking.system.service.IParkInfoService;
import com.laoayu.parking.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserParkMapper userParkMapper;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IParkInfoService parkInfoService;

    @Resource
    private UserMapper userMapper;

    @Override
    public Map<String, Object> login(User user) {
        // 根据用户名查询
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,user.getUserName());
        User loginUser = this.baseMapper.selectOne(wrapper);

        // 结果不为空，并且密码和传入密码匹配，则生成token
        if(loginUser != null && passwordEncoder.matches(user.getPassword(),loginUser.getPassword())){
            // 终极方案是jwt
            loginUser.setPassword(null);

            // 创建jwt
            String token = jwtUtil.createToken(loginUser);

            // 返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        User loginUser = null;
        try {
            loginUser = jwtUtil.parseToken(token, User.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(loginUser != null){
            //User loginUser = JSON.parseObject(JSON.toJSONString(obj),User.class);
            Map<String, Object> data = new HashMap<>();
            data.put("name",loginUser.getUserName());
            data.put("avatar", loginUser.getAvatar());
            data.put("nickName", loginUser.getNickName());
            data.put("email", loginUser.getEmail());
            data.put("phonenumber", loginUser.getPhonenumber());
            data.put("sex", loginUser.getSex());


            // 角色
            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getUserId());
            data.put("roles",roleList);

            // 权限列表(菜单列表)
            List<Menu> menuList = menuService.getMenuListByUserId(loginUser.getUserId());
            data.put("menuList",menuList);

            //管理的停车场信息
            List<ParkInfo> parkInfoList = parkInfoService.getParkInfoByUserId(loginUser.getUserId());
            data.put("parkInfoList",parkInfoList);

            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    @Transactional
    public void addUser(User user) {

        //写入用户表
        this.baseMapper.insert(user);

        //写入用户角色表
        List<Long> roleIdList = user.getRoleIdList();
        if (roleIdList != null){
            for (Long roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(user.getUserId(),roleId));
            }
        }

        //写入用户和管理停车对应表
        List<Long> parkIdList = user.getParkIdList();
        if (parkIdList != null) {
            for (Long parkId : parkIdList) {
                userParkMapper.insert(new UserPark(user.getUserId(),parkId));
            }
        }
    }

    @Override
    public User getUserById(Long userId) {

        //根据userId查数据
        User user = this.baseMapper.selectById(userId);

        //查用户对应角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId);
        List<UserRole> userRoleList = userRoleMapper.selectList(wrapper);

        List<Long> roleIdList = userRoleList.stream()
                .map(userRole -> {return userRole.getRoleId();})
                .collect(Collectors.toList());

        user.setRoleIdList(roleIdList);

        //查询用户所管理的停车场
        LambdaQueryWrapper<UserPark> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserPark::getUserId,userId);
        List<UserPark> userParkList = userParkMapper.selectList(wrapper1);

        List<Long> parkIdList = userParkList.stream()
                .map(userPark -> {return userPark.getParkId();})
                .collect(Collectors.toList());

        user.setParkIdList(parkIdList);

        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {

        //更新用户表
        this.baseMapper.updateById(user);

        //删除原有角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,user.getUserId());

        userRoleMapper.delete(wrapper);

        //设置新角色
        List<Long> roleIdList = user.getRoleIdList();
        if (roleIdList != null){
            for (Long roleId : roleIdList) {
                userRoleMapper.insert(new UserRole(user.getUserId(),roleId));
            }
        }

        //删除原来管辖停车
        LambdaQueryWrapper<UserPark> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserPark::getUserId,user.getUserId());

        userParkMapper.delete(wrapper1);

        //设置新管理的停车场
        List<Long> parkIdList = user.getParkIdList();
        if (parkIdList != null){
            for (Long parkId : parkIdList) {
                userParkMapper.insert(new UserPark(user.getUserId(),parkId));
            }
        }
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        //删除用户表
        this.baseMapper.deleteById(userId);
        //删除相关角色
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userId);

        userRoleMapper.delete(wrapper);

        //删除相关管理的停车场关联信息
        LambdaQueryWrapper<UserPark> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(UserPark::getUserId,userId);

        userParkMapper.delete(wrapper1);
    }

    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
