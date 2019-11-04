package com.ezlinker.app.modules.user.controller;


import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import com.ezlinker.common.web.AbstractXController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-04
 */
@RestController
@RequestMapping("/user/user")
public class UserController extends AbstractXController<User> {

    public UserController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    public R add(User user) {
        return null;
    }

    @Override
    public R delete(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    public R batchDelete(Integer[] ids) {
        return null;
    }

    @Override
    public R update(User user) {
        return null;
    }

    @Override
    public R get(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    public R list(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    public Object page(QueryCondition<User> queryCondition, int pageNo, int pageSize) {
        return null;
    }
}

