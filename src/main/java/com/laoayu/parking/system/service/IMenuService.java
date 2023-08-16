package com.laoayu.parking.system.service;

import com.laoayu.parking.system.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getAllMenu();

    List<Menu> getMenuListByUserId(Long userId);
}
