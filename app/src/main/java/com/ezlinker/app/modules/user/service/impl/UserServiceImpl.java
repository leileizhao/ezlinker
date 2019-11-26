package com.ezlinker.app.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.permission.model.UserRolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.mapper.UserMapper;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.model.UserInfoView;
import com.ezlinker.app.modules.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@Validated
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserRoleView> getRoles(Long userId) {
        return userMapper.getRoles(userId);
    }

    @Override
    public List<RolePermissionView> getPermissions(Long roleId) {
        return userMapper.getPermissions(roleId);
    }

    @Override
    public List<String> getAllPermissions(Long userId) {
        // 筛选出所有的用户权限
        List<UserRolePermissionView> userRolePermissionViews = userMapper.getUserPermissions(userId);
        // 构建授权列表
        List<String> userPermissions = new ArrayList<>();
        for (UserRolePermissionView urp : userRolePermissionViews) {
            if (urp.getAllow() == null || urp.getAllow().length() < 2) {
                userPermissions.add("[ALL]::" + urp.getResource() + "::" + urp.getMethods());
            } else {
                userPermissions.add(urp.getAllow() + "::" + urp.getResource() + "::" + urp.getMethods());
            }
        }
        return userPermissions;
    }

    @Override
    public UserInfoView getUserInfo(@NotNull(message = "用户ID不能为空") Long userId) {
        return userMapper.getUserInfo(userId);
    }
}
