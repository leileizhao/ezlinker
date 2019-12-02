package com.ezlinker.app.modules.module.controller;


import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.module.model.Module;
import com.ezlinker.app.modules.module.service.IModuleService;
import com.ezlinker.app.utils.ComponentTokenUtil;
import com.ezlinker.app.utils.IDKeyUtil;
import com.ezlinker.common.exception.BadRequestException;
import com.ezlinker.common.exception.BizException;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 前端控制器
 * 统一入口：
 * 当模块是Mqtt协议的时候，Username、password、clientId生效，当前版本我们决定用ClientId来鉴权；
 * 当模块是 Http或者COAP协议，需要用Username和Password组合去拿Token，然后通过算法生成Entry；
 * 数据入口URL：POST http：//entry.ezlinker.cn/entry/{entryCode},
 * 其中POST的数据里面应该包含：token，entryCode和Token计算后得到的是clientId；如果解析失败说明不合法；
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@RestController
@RequestMapping("/modules")
public class ModuleController extends AbstractXController<Module> {


    @Resource
    IModuleService iModuleService;

    public ModuleController(HttpServletRequest httpServletRequest) {
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
     * @param module
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody @Valid Module module) throws XException {

        if (module.getDataArea() != null) {
            int length = module.getDataArea().size();
            int require = 0;
            for (Object o : module.getDataArea()) {
                HashMap area = (HashMap) o;
                if (area.containsKey("field") && area.containsKey("label")) {
                    require++;
                } else {
                    throw new BadRequestException("DataAreas `field` and `label` fields required", "数据域 `name` and `label`字段必传");
                }
            }
            if (length == require) {
                module.setDataArea(module.getDataArea());
            } else {
                throw new BadRequestException("DataAreas `field` and `label` fields required", "数据域 `name` and `label`字段必传");
            }

        }

        String sn = IDKeyUtil.generateId().toString();
        String clientId = SecureUtil.sha1(sn);
        String username = SecureUtil.sha1(clientId);
        String password = SecureUtil.sha1(username);
        // 生成给Token，格式：clientId::[field1,field2,field3······]
        // token里面包含了模块的字段名,这样在数据入口处可以进行过滤。
        List<String> fields = new ArrayList<>();
        if (module.getDataArea() != null) {
            for (Object o : module.getDataArea()) {
                HashMap field = (HashMap) o;
                fields.add(field.get("field").toString());
            }

        }

        String token = ComponentTokenUtil.token(clientId + "::" + fields.toString());

        module.setSn(sn)
                .setClientId(clientId)
                .setUsername(username)
                .setPassword(password)
                .setToken(token);
        boolean ok = iModuleService.save(module);
        module.setFeatureList(iModuleService.getFeatureList(module.getId()));
        return ok ? data(module) : fail();


    }

    /**
     * 更新
     *
     * @param id
     * @param form
     * @return
     * @throws XException
     */
    @Override
    protected R update(@PathVariable Long id, @RequestBody Module form) throws XException {
        Module module = iModuleService.getById(id);
        if (module == null) {
            throw new BizException("Component not exists", "模块不存在");

        }
        if (!StringUtils.isEmpty(form.getType())) {
            module.setType(form.getType());

        }

        if (!StringUtils.isEmpty(form.getName())) {
            module.setName(form.getName());

        }
        if (!StringUtils.isEmpty(form.getProtocol())) {
            module.setProtocol(form.getProtocol());

        }
        if (!StringUtils.isEmpty(form.getModel())) {
            module.setModel(form.getModel());

        }
        if (form.getDataArea() != null) {
            module.setDataArea(form.getDataArea());
        }
        if (!StringUtils.isEmpty(form.getDescription())) {
            module.setDescription(form.getDescription());

        }
        boolean ok = iModuleService.updateById(module);

        module.setFeatureList(iModuleService.getFeatureList(module.getId()));
        return ok ? data(module) : fail();
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
        boolean ok = iModuleService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 获取字典项列表
     *
     * @param current
     * @param size
     * @param productId
     * @param type
     * @param name
     * @param protocol
     * @param model
     * @param sn
     * @return
     */
    @GetMapping
    public R queryForPage(
            @RequestParam Long current,
            @RequestParam Long size,
            @RequestParam Long productId,
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer protocol,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String sn) {
        QueryWrapper<Module> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId)
                .eq(type != null, Module.Fields.type, type)
                .eq(protocol != null, Module.Fields.protocol, protocol)
                .eq(model != null, Module.Fields.model, model)
                .eq(sn != null, Module.Fields.sn, sn)
                .like(name != null, Module.Fields.name, name);

        queryWrapper.orderByDesc("create_time");
        IPage<Module> iPage = iModuleService.page(new Page<>(current, size), queryWrapper);
        for (Module module : iPage.getRecords()) {
            module.setFeatureList(iModuleService.getFeatureList(module.getId()));
        }

        return data(iPage);
    }

    /**
     * 获取详情
     *
     * @param id
     * @return
     * @throws XException
     */
    @Override
    protected R get(@PathVariable Long id) throws XException {
        Module module = iModuleService.getById(id);
        if (module == null) {
            throw new BizException("Component not exists", "模块不存在");

        }
        module.setFeatureList(iModuleService.getFeatureList(module.getId()));
        return data(module);
    }
}

