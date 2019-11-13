package com.ezlinker.app.modules.user.controller;


import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


    /**
     * 获取用户的操作菜单
     *
     * @return
     * @throws XException
     */
    @GetMapping("/menu")
    public R getMenu() throws XException {
        return data(iUserService.getAllPermissions(getUserDetail().getId()));
    }

    /**
     * 获取用户详情
     *
     * @return
     * @throws XException
     */
    @GetMapping("/userInfo")
    public R getUserInfo() throws XException {
        return data(iUserService.getUserInfo(getUserDetail().getId()));
    }


}

