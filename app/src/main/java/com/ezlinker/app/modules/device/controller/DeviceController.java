package com.ezlinker.app.modules.device.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.device.model.Device;
import com.ezlinker.app.modules.device.service.IDeviceService;
import com.ezlinker.app.modules.module.model.Module;
import com.ezlinker.app.modules.module.service.IModuleService;
import com.ezlinker.app.modules.mqtttopic.model.MqttTopic;
import com.ezlinker.app.modules.mqtttopic.service.IMqttTopicService;
import com.ezlinker.app.modules.product.model.Product;
import com.ezlinker.app.modules.product.service.IProductService;
import com.ezlinker.app.modules.project.model.Project;
import com.ezlinker.app.modules.project.service.IProjectService;
import com.ezlinker.app.modules.relation.service.IDeviceModuleService;
import com.ezlinker.app.modules.relation.service.IProductModuleService;
import com.ezlinker.app.modules.tag.model.Tag;
import com.ezlinker.app.modules.tag.service.ITagService;
import com.ezlinker.app.utils.IDKeyUtil;
import com.ezlinker.common.exception.BizException;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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
    // 发布权限
    private static final int TOPIC_PUB = 1;
    // 订阅权限
    private static final int TOPIC_SUB = 2;

    @Resource
    IModuleService iModuleService;
    @Resource
    IDeviceService iDeviceService;
    @Resource
    IProjectService iProjectService;
    @Resource
    IProductService iProductService;
    @Resource
    ITagService iTagService;
    @Resource
    IMqttTopicService iMqttTopicService;
    @Resource
    IDeviceModuleService iDeviceModuleService;
    @Resource
    IProductModuleService iProductModuleService;

    public DeviceController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 新建一个设备
     *
     * @param form
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody @Valid Device form) throws XException {
        Project project = iProjectService.getById(form.getProjectId());
        Product product = iProductService.getById(form.getProductId());
        if (project == null) {
            throw new BizException("Project not exists", "该项目不存在!");
        }
        if (product == null) {
            throw new BizException("Product not exists", "该产品不存在!");
        }
        Device device = new Device();
        device.setName(form.getName())
                .setLogo(form.getLogo())
                .setDescription(form.getDescription())
                .setModel(form.getModel())
                .setIndustry(form.getIndustry())
                .setSn("SN" + IDKeyUtil.generateId().toString())
                .setProductId(form.getProductId())
                .setProjectId(form.getProjectId());

        // 建立 设备和模块的关系表
        List<Module> moduleList = iModuleService.list(new QueryWrapper<Module>().eq("product_id", product.getId()));
        for (Module module : moduleList) {
            // Clone 新的Module
            Module newModule = new Module();
            BeanUtil.copyProperties(module, newModule);
            String clientId = IDKeyUtil.generateId().toString();
            String username = SecureUtil.md5(clientId);
            String password = SecureUtil.md5(username);
            newModule.setClientId(clientId).setUsername(username).setPassword(password);
            iModuleService.save(newModule);
            // 给新的Module创建Topic
            MqttTopic s2cTopic = new MqttTopic();
            s2cTopic.setAccess(TOPIC_PUB)
                    .setClientId(clientId)
                    .setDeviceId(device.getId())
                    .setTopic("mqtt/out/" + device.getId() + "/" + clientId + "/s2c")
                    .setUsername(username)
                    .setDescription("服务端消息入口");


        }

        boolean ok = iDeviceService.save(device);


        // 保存Tag
        if (ok) {
            String[] tags = form.getTags();
            for (String tag : tags) {
                Tag t = new Tag();
                t.setType(2);
                t.setName(tag).setLinkId(product.getProjectId());
                iTagService.save(t);
            }

        }

        // Topic生成

        //行为类型: 1=订阅2=发布3=订阅+发布'
//        /**
//         * 下发指令
//         */
//        MqttTopic s2cTopic = new MqttTopic();
//        s2cTopic.setAccess(TOPIC_PUB).setClientId(clientId).setTopic("mqtt/out/" + clientId + "/s2c").setUsername(username).setDescription("服务端消息入口");
//        /**
//         * 上传
//         */
//        MqttTopic c2sTopic = new MqttTopic();
//        c2sTopic.setAccess(TOPIC_SUB).setClientId(clientId).setTopic("mqtt/in/" + clientId + "/c2s").setUsername(username).setDescription("服务端消息出口");
//        /**
//         * 上报状态
//         */
//        MqttTopic statusTopic = new MqttTopic();
//        statusTopic.setAccess(TOPIC_SUB).setClientId(clientId).setTopic("mqtt/in/" + clientId + "/status").setDescription("状态上报入口");
//
//        /**
//         * 接受分组指令
//         */
//        MqttTopic groupTopic = new MqttTopic();
//        groupTopic.setAccess(TOPIC_SUB).setClientId(clientId).setTopic("mqtt/out/" + SecureUtil.md5(getUserDetail().getId().toString()) + clientId + "/group").setUsername(username).setDescription("分组接收消息入口");
//
//        iMqttTopicService.save(s2cTopic);
//        iMqttTopicService.save(c2sTopic);
//        iMqttTopicService.save(statusTopic);
//        iMqttTopicService.save(groupTopic);

        return ok ? data(device) : fail();
    }
}

