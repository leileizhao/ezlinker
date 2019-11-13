package com.ezlinker.app.modules.role.controller;


import com.ezlinker.app.modules.role.model.Role;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractXController<Role> {

    public RoleController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

}

