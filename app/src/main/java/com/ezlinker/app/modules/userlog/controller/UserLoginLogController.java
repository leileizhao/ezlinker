package com.ezlinker.app.modules.userlog.controller;


import com.ezlinker.app.modules.userlog.model.UserLoginLog;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户登录日志 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/userlog")
public class UserLoginLogController extends AbstractXController<UserLoginLog> {

    public UserLoginLogController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(UserLoginLog userLoginLog) throws XException {
        return null;
    }

    @Override
    protected R delete(Integer[] ids) throws XException {
        return null;
    }

    @Override
    protected R update(UserLoginLog userLoginLog) throws XException {
        return null;
    }

    @Override
    protected R get(Long id) throws XException {
        return null;
    }
}

