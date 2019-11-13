package com.ezlinker.app.modules.permission.controller;


import com.ezlinker.app.modules.permission.model.Permission;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户权限 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/permission")
public class PermissionController extends AbstractXController<Permission> {

    public PermissionController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

}

