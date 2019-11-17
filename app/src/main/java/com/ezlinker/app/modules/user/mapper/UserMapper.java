package com.ezlinker.app.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.permission.model.UserRolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.model.UserInfoView;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
public interface UserMapper extends BaseMapper<User> {

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
    /**
     * 查询用户的权限列表
     *
     * @param roleId
     * @return
     */
    List<UserRolePermissionView> getUserPermissions(Long roleId);

    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    UserInfoView getUserInfo(Long userId);
}
