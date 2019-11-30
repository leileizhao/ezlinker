package com.ezlinker.app.modules.relation.controller;


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
 * @since 2019-11-30
 */
@RestController
@RequestMapping("/relations")
public class FeatureModuleController extends AbstractXController {

    public FeatureModuleController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }
}

