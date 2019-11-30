package com.ezlinker.app.modules.feature.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.feature.service.IFeatureService;
import com.ezlinker.app.modules.relation.model.FeatureModule;
import com.ezlinker.app.modules.relation.service.IFeatureModuleService;
import com.ezlinker.common.exception.BadRequestException;
import com.ezlinker.common.exception.BizException;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
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
 * 产品的功能（特性），和设备是多对多的关系，通过中间表关联起来 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/features")
public class FeatureController extends AbstractXController<Feature> {
    @Resource
    IFeatureService iFeatureService;

    @Resource
    IFeatureModuleService iFeatureModuleService;
    public FeatureController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 获取当前支持的功能的类型，目前暂时支持2种
     *
     * @return
     */
    @GetMapping("/types")
    public R getType() {
        HashMap<String, Object> data1 = new HashMap<>();
        data1.put("name", "switch");
        data1.put("label", "开关");
        data1.put("value", "1");

        HashMap<String, Object> data2 = new HashMap<>();
        data2.put("name", "button");
        data2.put("label", "按钮");
        data2.put("value", "2");

        List<HashMap<String, Object>> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        return data(list);
    }

    @Override
    protected R get(@PathVariable Long id) throws XException {
        Feature feature = iFeatureService.getById(id);
        if (feature == null) {
            throw new XException("Feature not exists!", "功能不存在");
        }
        return data(feature);
    }

    /**
     * 获取产品的功能列表
     *
     * @param productId
     * @return
     */
    @GetMapping
    public R getByProduct(@RequestParam Long productId,
                          @RequestParam Integer current,
                          @RequestParam Integer size) {

        QueryWrapper<Feature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId).orderByDesc("create_time");

        return data(iFeatureService.page(new Page<>(current, size), queryWrapper));
    }

    /**
     * 添加
     *
     * @param feature
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody @Valid Feature feature) throws XException {
        if (feature.getName() == null) {
            throw new BadRequestException("Must specify a name", "必须指定名称");

        }
        if (feature.getLabel() == null) {
            throw new BadRequestException("Must specify a label", "必须指定标签");

        }


        if (feature.getProductId() == null) {
            throw new BadRequestException("Must specify a product", "必须指定产品");

        }
        if (feature.getCmdKey() == null) {
            throw new BadRequestException("Must specify a key", "必须指定命令Key");

        }
        if (feature.getCmdValue() == null) {
            throw new BadRequestException("Must specify cmdValue", "必须指定命令内容");

        }

        if (feature.getModuleId() != null) {

            FeatureModule featureModule=new FeatureModule();
            featureModule.setFeatureId(feature.getId()).setModuleId(feature.getModuleId());
            iFeatureModuleService.save(featureModule);

        }

        boolean ok = iFeatureService.save(feature);
        return ok ? data(feature) : fail();
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
    protected R update(@PathVariable Long id, @RequestBody @Valid Feature form) throws XException {
        Feature feature = iFeatureService.getById(id);
        if (feature == null) {
            throw new BizException("Feature not exists!", "功能不存在");
        }

        if (form.getName() != null) {
            feature.setName(form.getName());
        }
        if (form.getLabel() != null) {

            feature.setLabel(form.getLabel());
        }

        if (form.getCmdKey() != null) {
            feature.setCmdKey(feature.getCmdKey());
        }
        boolean ok = iFeatureService.save(feature);
        return ok ? data(feature) : fail();
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     * @throws XException
     */
    @Override
    protected R delete(@PathVariable Integer[] ids) throws XException {
        boolean ok = iFeatureService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }


}

