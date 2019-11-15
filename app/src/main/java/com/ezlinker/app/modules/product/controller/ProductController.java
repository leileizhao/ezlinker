package com.ezlinker.app.modules.product.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.product.model.Product;
import com.ezlinker.app.modules.product.service.IProductService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 * 产品（设备的抽象模板） 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/products")
public class ProductController extends AbstractXController<Product> {

    @Autowired
    IProductService iProductService;

    public ProductController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }


    /**
     * 创建产品
     *
     * @param product 产品:必传
     * @return
     */
    @Override
    protected R add(@RequestBody @Valid Product product) {
        if (product.getParamMap() != null) {
            product.setParameter(product.getParamMap().toString());
        } else {
            JSONObject defaultJson = new JSONObject();
            defaultJson.put("name", "状态");
            defaultJson.put("field", "state");
            product.setParameter(defaultJson.toJSONString());
        }
        boolean ok = iProductService.save(product);
        return ok ? data(product) : fail();
    }

    /**
     * 删除产品
     *
     * @param ids 批量删除的ID数组:必传
     * @return
     */

    @Override
    protected R delete(@PathVariable Integer[] ids) {
        boolean ok = iProductService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 更新产品信息
     *
     * @param product 产品:必传
     * @return
     * @throws XException
     */
    @Override
    protected R update(@PathVariable Long id, @RequestBody Product product) throws XException {

        if (iProductService.getById(id) == null) {
            throw new XException("Product not exists!", "产品不存在");
        }
        product.setId(id);

        if (product.getParamMap() != null) {
            product.setParameter(product.getParamMap().toString());
        }
        boolean ok = iProductService.updateById(product);
        return ok ? data(product) : fail();
    }


    /**
     * 查看产品详情
     *
     * @param id 产品ID:必传
     * @return
     */

    @Override
    protected R get(@PathVariable Long id) throws XException {
        Product project = iProductService.getById(id);
        if (project == null) {
            throw new XException("Product not exists!", "产品不存在");
        }
        return data(project);
    }

    /**
     * 查询
     *
     * @param projectId 所属项目ID
     * @param tag       标签
     * @param name      名称
     * @param type      类型
     * @param pageNo    页码
     * @param pageSize  页长
     * @return
     */
    @GetMapping
    public R queryForPage(
            @RequestParam Long projectId,
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer type) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq(tag != null, "tag", tag);
        queryWrapper.eq(type != null, "type", type);
        queryWrapper.like(name != null, "name", name);

        queryWrapper.orderByDesc("create_time");
        IPage<Product> projectPage = iProductService.page(new Page<>(pageNo, pageSize), queryWrapper);

        return data(projectPage);
    }
}

