package com.laoayu.parking.system.mapper;

import com.laoayu.parking.system.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    public List<Long> getMenuIdListByRoleId(Long roleId);

}
