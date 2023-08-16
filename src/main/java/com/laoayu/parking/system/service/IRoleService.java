package com.laoayu.parking.system.service;

import com.laoayu.parking.system.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author laoayu
 * @since 2023-03-07
 */
public interface IRoleService extends IService<Role> {

    void addRole(Role role);

    Role getRoleById(Long roleId);

    void updateRole(Role role);

    void deleteRoleById(Long roleId);
}
