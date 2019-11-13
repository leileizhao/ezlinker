package com.ezlinker.app.modules.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.mapper.UserMapper;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.model.UserInfoView;
import com.ezlinker.app.modules.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        List<UserRoleView> userRoleViews = userMapper.getRoles(userId);
        List<String> userPermissions = new ArrayList<>();
        for (UserRoleView userRoleView : userRoleViews) {
            List<RolePermissionView> rolePermissionViews = userMapper.getPermissions(userRoleView.getId());
            for (RolePermissionView rolePermissionView : rolePermissionViews) {
                if (rolePermissionView.getMethods() == null || rolePermissionView.getMethods().length() == 0) {
                    userPermissions.add("ALL::" + rolePermissionView.getResource());

                } else {
                    userPermissions.add(rolePermissionView.getMethods() + "::" + rolePermissionView.getResource());

                }
            }
        }
        return userPermissions;
    }

    @Override
    public UserInfoView getUserInfo(Long userId) {
        return userMapper.getUserInfo(userId);
    }
}
