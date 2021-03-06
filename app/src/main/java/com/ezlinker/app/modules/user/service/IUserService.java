package com.ezlinker.app.modules.user.service;

import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ezlinker.app.modules.user.model.UserInfoView;

import javax.validation.constraints.NotNull;
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

    /**
     * 获取权限表:
     * role:权限:资源
     * @param userId
     * @return
     */
    List<String> getAllPermissions(Long userId);
    /**
     * 查询用户详情
     * @param userId
     * @return
     */
    UserInfoView getUserInfo(@NotNull(message = "用户ID不能为空") Long userId);
}
