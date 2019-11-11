package com.ezlinker.app.modules.user.controller;


import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/users")
public class UserController extends AbstractXController<User> {

    @Autowired
    IUserService iUserService;

    public UserController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(User user) throws XException {
        return null;
    }

    @Override
    protected R delete(Integer[] ids) throws XException {
        return null;
    }

    @Override
    protected R update(User user) throws XException {
        return null;
    }

    @Override
    protected R get(Long id) throws XException {
        return null;
    }

    /**
     * 获取用户的操作菜单
     *
     * @return
     * @throws XException
     */
    @GetMapping("/menu")
    public R getMenu() throws XException {
        List<UserRoleView> userRoleViews = iUserService.getRoles(getUserDetail().getId());
        List<String> userPermissions = new ArrayList<>();
        for (UserRoleView userRoleView : userRoleViews) {
            List<RolePermissionView> rolePermissionViews = iUserService.getPermissions(userRoleView.getId());
            for (RolePermissionView rolePermissionView : rolePermissionViews) {
                userPermissions.add(userRoleView.getName() + ":" + rolePermissionView.getName());
            }
        }
        return data(userPermissions);
    }
}

