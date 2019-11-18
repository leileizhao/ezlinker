package com.ezlinker.app.modules.feature.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.feature.service.IFeatureService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

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
    @Autowired
    IFeatureService iFeatureService;

    public FeatureController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
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
    public R getByProduct(@RequestParam Long productId) {

        QueryWrapper<Feature> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId).orderByDesc("create_time");

        return data(iFeatureService.list(queryWrapper));
    }

    /**
     * 添加功能
     *
     * @param feature 功能
     * @return
     * @throws XException
     */
    @Override
    protected R add(@RequestBody Feature feature) throws XException {
        boolean ok = iFeatureService.save(feature);
        return ok ? success() : fail();
    }

    /**
     * 删除功能
     *
     * @param ids 功能id
     * @return
     * @throws XException
     */
    @Override
    protected R delete(@RequestBody Integer[] ids) throws XException {
        boolean ok = iFeatureService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 更新功能信息
     *
     * @param id      功能id
     * @param feature 功能
     * @return
     * @throws XException
     */
    @Override
    protected R update(Long id, Feature feature) throws XException {
        if (iFeatureService.getById(id) == null) {
            throw new XException("Feature not exists!", "功能不存在");
        }
        feature.setId(id);
        boolean ok = iFeatureService.updateById(feature);
        return ok ? data(feature) : fail();
    }

}

