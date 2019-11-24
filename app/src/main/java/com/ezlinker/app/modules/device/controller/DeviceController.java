package com.ezlinker.app.modules.device.controller;


import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.device.model.Device;
import com.ezlinker.app.modules.device.service.IDeviceService;
import com.ezlinker.app.modules.product.model.Product;
import com.ezlinker.app.modules.product.service.IProductService;
import com.ezlinker.app.modules.project.model.Project;
import com.ezlinker.app.modules.project.service.IProjectService;
import com.ezlinker.app.utils.IDKeyUtil;
import com.ezlinker.common.exception.BizException;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Resource
    IDeviceService iDeviceService;
    @Resource
    IProjectService iProjectService;
    @Resource
    IProductService iProductService;
    public DeviceController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     *  新建一个设备
     * @param form
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody @Valid Device form) throws XException {
        Project project =iProjectService.getById(form.getProjectId());
        Product product=iProductService.getById(form.getProductId());
        if (project==null){
            throw new BizException("Project not exists","该项目不存在!");
        }
        if (product==null){
            throw new BizException("Product not exists","该产品不存在!");
        }
        Device device=new Device();
        device.setName(form.getName())
                .setLogo(form.getLogo())
                .setDescription(form.getDescription())
                .setModel(form.getModel())
                .setIndustry(form.getIndustry())
                .setTag(form.getTag())
                .setSn("SN"+IDKeyUtil.generateId().toString())
                .setProductId(form.getProductId())
                .setProjectId(form.getProjectId());
        // Topic生成
        boolean ok = iDeviceService.save(device);
        return ok ? data(device) : fail();
    }
}

