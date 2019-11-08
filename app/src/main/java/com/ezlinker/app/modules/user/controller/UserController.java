package com.ezlinker.app.modules.user.controller;


import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import com.ezlinker.app.common.AbstractXController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-05
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractXController<User> {


    public UserController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(User user) {
        return null;
    }

    @Override
    protected R delete(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    protected R batchDelete(Integer[] ids) {
        return null;
    }

    @Override
    protected R update(User user) {
        return null;
    }

    @Override
    protected R get(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    protected R list(QueryCondition<User> queryCondition) {
        return null;
    }

    @Override
    protected Object page(QueryCondition<User> queryCondition, int pageNo, int pageSize) {
        return null;
    }
}
