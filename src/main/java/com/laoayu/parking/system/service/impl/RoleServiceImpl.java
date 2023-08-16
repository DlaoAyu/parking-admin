package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laoayu.parking.system.entity.Role;
import com.laoayu.parking.system.entity.RoleMenu;
import com.laoayu.parking.system.mapper.RoleMapper;
import com.laoayu.parking.system.mapper.RoleMenuMapper;
import com.laoayu.parking.system.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional
    public void addRole(Role role) {
        //写入角色表
        this.baseMapper.insert(role);

        //写入角色菜单对应表
        if ( null != role.getMenuIdList() ){
            for (Long menuId : role.getMenuIdList()){
                roleMenuMapper.insert(new RoleMenu(role.getRoleId(), menuId));
            }
        }
    }

    //查询角色（同时查出角色所对应的菜单（角色菜单回显））
    @Override
    public Role getRoleById(Long roleId) {

        Role role = this.baseMapper.selectById(roleId);
        List<Long> menuIdList = roleMenuMapper.getMenuIdListByRoleId(roleId);
        role.setMenuIdList(menuIdList);
        return role;
    }

    //修改角色信息（可以同时更改角色和菜单对应表）
    @Override
    @Transactional
    public void updateRole(Role role) {
        //修改角色表
        this.baseMapper.updateById(role);

        //删除原有权限（其实就是删除角色和菜单对应表）
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,role.getRoleId());
        roleMenuMapper.delete(wrapper);
        //新增权限
        if ( null != role.getMenuIdList() ){
            for (Long menuId : role.getMenuIdList()){
                roleMenuMapper.insert(new RoleMenu(role.getRoleId(), menuId));
            }
        }
    }

    @Override
    @Transactional
    public void deleteRoleById(Long roleId) {
        this.baseMapper.deleteById(roleId);
        //删除权限
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,roleId);
        roleMenuMapper.delete(wrapper);
    }
}
