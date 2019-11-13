package com.ezlinker.app.modules.system.controller;

import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.utils.SystemPropertiesUtil;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ezlinker
 * @description: 系统数据获取
 * @author: wangwenhai
 * @create: 2019-11-13 10:07
 **/
@RestController
@RequestMapping("/system")
public class SystemController extends AbstractXController {
    public SystemController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 获取系统参数
     *
     * @return
     */
    @GetMapping("/properties")
    public R getSystemProperties() {
        return data(SystemPropertiesUtil.getSystemProperties());
    }


    @GetMapping("/running")
    public R running() {
        return data(SystemPropertiesUtil.getRunning());
    }

}
