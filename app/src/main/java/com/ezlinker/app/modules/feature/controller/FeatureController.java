package com.ezlinker.app.modules.feature.controller;


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
@RequestMapping("/feature")
public class FeatureController extends AbstractXController {

    public FeatureController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(Object o) {
        return null;
    }

    @Override
    protected R delete(QueryCondition queryCondition) {
        return null;
    }

    @Override
    protected R batchDelete(Integer[] ids) {
        return null;
    }

    @Override
    protected R update(Object o) {
        return null;
    }

    @Override
    protected R get(QueryCondition queryCondition) {
        return null;
    }

    @Override
    protected R list(QueryCondition queryCondition) {
        return null;
    }

    @Override
    protected Object page(QueryCondition queryCondition, int pageNo, int pageSize) {
        return null;
    }
}

