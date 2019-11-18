package com.ezlinker.app.modules.component.controller;


import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.component.model.Component;
import com.ezlinker.app.modules.component.service.IComponentService;
import com.ezlinker.app.modules.mqtttopic.model.MqttTopic;
import com.ezlinker.app.modules.mqtttopic.service.IMqttTopicService;
import com.ezlinker.app.utils.IDKeyUtil;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    IMqttTopicService iMqttTopicService;
    public ComponentController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 获取当前支持的协议的类型，目前暂时支持2种
     *
     * @return
     */
    @GetMapping("/protocols")
    public R getProtocols() {
        HashMap<String, Object> data1 = new HashMap<>();
        data1.put("name", "MQTT");
        data1.put("label", "MQTT协议");
        data1.put("value", "1");

        HashMap<String, Object> data2 = new HashMap<>();
        data2.put("name", "HTTP");
        data2.put("label", "HTTP协议");
        data2.put("value", "2");

        List<HashMap<String, Object>> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        return data(list);
    }

    /**
     * 获取当前支持的类型的类型，目前暂时支持2种
     *
     * @return
     */
    @GetMapping("/types")
    public R getType() {
        HashMap<String, Object> data1 = new HashMap<>();
        data1.put("label", "家用");
        data1.put("value", "1");

        HashMap<String, Object> data2 = new HashMap<>();
        data2.put("label", "工控");
        data2.put("value", "2");

        HashMap<String, Object> data3 = new HashMap<>();
        data3.put("label", "模组");
        data3.put("value", "3");

        HashMap<String, Object> data4 = new HashMap<>();
        data4.put("label", "单片机");
        data4.put("value", "4");

        List<HashMap<String, Object>> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);

        return data(list);
    }

    /**
     * 创建模块
     *
     * @param component
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody Component component) throws XException {
        component.setDataArea(component.getDataArea());
        String sn = IDKeyUtil.generateId().toString();
        String token = SecureUtil.sha1(sn);
        String clientId = SecureUtil.sha1(sn);
        String username = SecureUtil.sha1(clientId);
        String password = SecureUtil.sha1(username);

        //MQTT
        //行为类型: 1=订阅2=发布3=订阅+发布'
        if (component.getProtocol() == 1) {
            /**
             * 下发指令
             */
            MqttTopic s2cTopic = new MqttTopic();
            s2cTopic.setAccess(1).setAllow(1).setClientId(clientId).setIp("0.0.0.0").setTopic("mqtt/out/" + clientId + "/s2c").setUsername(username);
            /**
             * 上传
             */
            MqttTopic c2sTopic = new MqttTopic();
            c2sTopic.setAccess(2).setAllow(1).setClientId(clientId).setIp("0.0.0.0").setTopic("mqtt/in/" + clientId + "/c2s").setUsername(username);
            /**
             * 上报状态
             */
            MqttTopic statusTopic = new MqttTopic();
            statusTopic.setAccess(2).setAllow(1).setClientId(clientId).setIp("0.0.0.0").setTopic("mqtt/in/" + clientId + "/status").setUsername(username);

            iMqttTopicService.save(s2cTopic);
            iMqttTopicService.save(c2sTopic);
            iMqttTopicService.save(statusTopic);


        }

        component.setSn(sn).setToken(token).setClientId(clientId).setUsername(username).setPassword(password);
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
        if (form.getDataArea() != null) {
            try {
                JSONArray.parse(form.getDataArea());
                component.setDataArea(form.getDataArea());

            } catch (Exception e) {
                throw new XException("Format error", "数据格式错误,数据域必须是一个JSON数组格式的字符串");
            }

        }
        if (!StringUtils.isEmpty(form.getDescription())) {
            component.setDescription(form.getDescription());

        }
        boolean ok = iComponentService.updateById(component);

        return ok ? data(component) : fail();
    }

    /**
     * 删除模块
     *
     * @param ids
     * @return
     * @throws XException
     */
    @Override
    protected R delete(@PathVariable Integer[] ids) throws XException {
        boolean ok = iComponentService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 获取字典项列表
     *
     * @param pageNo
     * @param pageSize
     * @param productId
     * @param type
     * @param name
     * @param protocol
     * @param model
     * @param tag
     * @param sn
     * @return
     */
    @GetMapping
    public R queryForPage(
            @RequestParam Long pageNo,
            @RequestParam Long pageSize,
            @RequestParam Long productId,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer protocol,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String sn) {
        QueryWrapper<Component> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId)
                .eq(type != null, Component.Fields.type, type)
                .eq(protocol != null, Component.Fields.protocol, protocol)
                .eq(model != null, Component.Fields.model, model)
                .eq(tag != null, Component.Fields.tag, tag)
                .eq(sn != null, Component.Fields.sn, sn)
                .like(name != null, Component.Fields.name, name);

        queryWrapper.orderByDesc("create_time");
        IPage<Component> page = iComponentService.page(new Page<>(pageNo, pageSize), queryWrapper);
        return data(page);
    }
}

