package com.ezlinker.app.modules.device.controller;


import com.ezlinker.app.modules.device.model.Device;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 实际设备，是产品的一个实例。 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/devices")
public class DeviceController extends AbstractXController<Device> {

    public DeviceController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(Device device) throws XException {
        return super.add(device);
    }
}

