package com.ezlinker.app.modules.user.controller;


import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

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
@RequestMapping("/user")
public class UserController extends AbstractXController<User> {

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
}

