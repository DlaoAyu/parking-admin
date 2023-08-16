package com.laoayu.parking.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.laoayu.parking.system.entity.Menu;
import com.laoayu.parking.system.mapper.MenuMapper;
import com.laoayu.parking.system.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> getAllMenu() {

        //查一级菜单
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Menu::getParentId,0);
        List<Menu> menuList = this.list(wrapper);

        //填充子菜单
        setMenuChildren(menuList);
        return menuList;

    }


    private void setMenuChildren(List<Menu> menuList) {
        if (menuList != null) {
            for (Menu menu : menuList) {
                LambdaQueryWrapper<Menu> subWrapper = new LambdaQueryWrapper();
                subWrapper.eq(Menu::getParentId, menu.getMenuId());
                List<Menu> subMenuList = this.list(subWrapper);
                menu.setChildren(subMenuList);
                // 递归调用
                setMenuChildren(subMenuList);
            }
        }
    }

    @Override
    public List<Menu> getMenuListByUserId(Long userId) {
        //查一级菜单
        List<Menu> menuList = this.baseMapper.getMenuListByUserId(userId, (long) 0);
        //查子菜单
        setMenuChildrenByUserId(userId, menuList);
        return menuList;
    }

    private void setMenuChildrenByUserId(Long userId, List<Menu> menuList) {
        if (menuList != null){
            for (Menu menu : menuList){
                List<Menu> subMenuList = this.baseMapper.getMenuListByUserId(userId, menu.getMenuId());//这一块不对劲可能会出错
                menu.setChildren(subMenuList);
                //递归
                setMenuChildrenByUserId(userId,subMenuList);
            }
        }
    }
}
