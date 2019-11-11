package com.ezlinker.app.modules.user.service;

import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
public interface IUserService extends IService<User> {
    /**
     * 查询角色列表
     *
     * @param userId
     * @return
     */
    List<UserRoleView> getRoles(Long userId);

    /**
     * 查询权限列表
     *
     * @param roleId
     * @return
     */
    List<RolePermissionView> getPermissions(Long roleId);
}
