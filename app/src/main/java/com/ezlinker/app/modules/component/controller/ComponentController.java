package com.ezlinker.app.modules.component.controller;


import cn.hutool.crypto.SecureUtil;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.component.model.Component;
import com.ezlinker.app.modules.component.service.IComponentService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@RestController
@RequestMapping("/components")
public class ComponentController extends AbstractXController<Component> {

    @Autowired
    IComponentService iComponentService;

    public ComponentController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R add(@RequestBody Component component) throws XException {


        boolean ok = iComponentService.save(component);

        return ok ? data(component) : fail();

    }

    @Override
    protected R update(@PathVariable Long id, @RequestBody Component form) throws XException {
        Component component = iComponentService.getById(id);
        if (component == null) {
            throw new XException("Component not exists", "模块不存在");

        }
        if (!StringUtils.isEmpty(form.getType())) {
            component.setType(form.getType());

        }
        if (!StringUtils.isEmpty(form.getState())) {
            component.setState(form.getState());

        }
        if (!StringUtils.isEmpty(form.getName())) {
            component.setName(form.getName());

        }
        if (!StringUtils.isEmpty(form.getProtocol())) {
            component.setProtocol(form.getProtocol());

        }
        if (!StringUtils.isEmpty(form.getModel())) {
            component.setModel(form.getModel());

        }
        if (form.getDataAreaMap() != null) {
            component.setDataArea(form.getDataAreaMap().toJSONString());

        }
        if (!StringUtils.isEmpty(form.getDescription())) {
            component.setDescription(form.getDescription());

        }

        return data(iComponentService.updateById(component));
    }
}

