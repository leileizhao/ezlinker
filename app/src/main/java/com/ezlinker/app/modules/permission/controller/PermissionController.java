package com.ezlinker.app.modules.permission.controller;


import com.ezlinker.common.exchange.QueryCondition;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/permission/permission")
public class PermissionController extends AbstractXController {

    public PermissionController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    public R add(Object o) {
        return null;
    }

    @Override
    public R delete(QueryCondition queryCondition) {
        return null;
    }

    @Override
    public R batchDelete(Integer[] ids) {
        return null;
    }

    @Override
    public R update(Object o) {
        return null;
    }

    @Override
    public R get(QueryCondition queryCondition) {
        return null;
    }

    @Override
    public R list(QueryCondition queryCondition) {
        return null;
    }

    @Override
    public Object page(QueryCondition queryCondition, int pageNo, int pageSize) {
        return null;
    }
}

