package com.ezlinker.app.modules.entry.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.modules.entry.form.UserLoginForm;
import com.ezlinker.app.modules.permission.model.RolePermissionView;
import com.ezlinker.app.modules.permission.service.IPermissionService;
import com.ezlinker.app.modules.role.model.UserRoleView;
import com.ezlinker.app.modules.role.service.IRoleService;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.app.utils.UserDetail;
import com.ezlinker.app.utils.UserTokenUtil;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.exchange.RCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: ezlinker
 * @description: 入口
 * @author: wangwenhai
 * @create: 2019-11-11 17:44
 **/
@RestController
@RequestMapping("/entry")
public class EntryController  {

    @Autowired
    IUserService iUserService;

    @Autowired
    IRoleService iRoleService;

    @Autowired
    IPermissionService iPermissionService;

    /**
     * 用户登录
     *
     * @param loginForm 登录表单
     * @return
     * @throws XException
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/login")
    public R login(@RequestBody UserLoginForm loginForm) throws XException {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", loginForm.getUsername()));
        if (user == null) {
            throw new XException(403, "Login failure,user not exists!", "登陆失败,用户不存在");
        }
        if (!user.getPassword().toUpperCase().equals(SecureUtil.md5(loginForm.getPassword()).toUpperCase())) {
            throw new XException(403, "Login failure,password invalid!", "登陆失败,密码错误");

        }
        /**
         * 构建UserDetail
         */
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user.getId());
        userDetail.setUsername(user.getUsername());
        userDetail.setUserType(user.getUserType());
        userDetail.setExpiredTime(7 * 24 * 60 * 60 * 100L);
        // 查询Role和Permission
        List<UserRoleView> userRoleViews = iUserService.getRoles(user.getId());
        //Roles
        List<String> userRoles = new ArrayList<>();
        List<String> userPermissions = new ArrayList<>();

        for (UserRoleView userRoleView : userRoleViews) {
            userRoles.add(userRoleView.getName());
            List<RolePermissionView> rolePermissionViews = iUserService.getPermissions(userRoleView.getId());
            for (RolePermissionView rolePermissionView : rolePermissionViews) {
                userPermissions.add(userRoleView.getName() + ":" + rolePermissionView.getName());
            }
        }
        userDetail.setRoles(userRoles);
        userDetail.setPermissions(userPermissions);
        String token = UserTokenUtil.token(userDetail, 24 * 60 * 60 * 1000L);

        return new R(RCode.SUCCESS.getCode(), RCode.SUCCESS.getMessage(), RCode.SUCCESS.getI8nMessage(), token);
    }

}
