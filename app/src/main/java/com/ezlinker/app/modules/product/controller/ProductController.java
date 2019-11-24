package com.ezlinker.app.modules.product.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezlinker.app.common.SimpleXController;
import com.ezlinker.app.modules.product.form.AddProductForm;
import com.ezlinker.app.modules.product.form.UpdateProductForm;
import com.ezlinker.app.modules.product.model.Product;
import com.ezlinker.app.modules.product.service.IProductService;
import com.ezlinker.app.modules.tag.model.Tag;
import com.ezlinker.app.modules.tag.service.ITagService;
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
 * 产品（设备的抽象模板） 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-13
 */
@RestController
@RequestMapping("/products")
public class ProductController extends SimpleXController {

    @Resource
    IProductService iProductService;
    @Resource
    ITagService iTagService;

    public ProductController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    /**
     * 检查参数合法性
     *
     * @param parameter
     * @return
     */
    private boolean check(List<HashMap<String, Object>> parameter) {
        if (parameter == null) return false;
        try {
            int parameterLength = parameter.size();
            int accepted = 0;
            for (HashMap map : parameter) {
                if (map.keySet().size() == 2) {
                    if ((map.containsKey("label") && map.containsKey("field"))) {
                        accepted++;
                    }
                }
            }
            return parameterLength == accepted;

        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 创建产品
     *
     * @param form 产品:必传
     * @return
     */
    @PostMapping
    protected R add(@RequestBody @Valid AddProductForm form) throws XException {
        Product product = new Product();
        if (form.getParameter() != null) {
            if (!check(form.getParameter())) {
                throw new BadRequestException("Parameter format invalid!", "Parameter 参数格式错误");
            }

        } else {
            product.setParameter(new ArrayList<>());
        }
        BeanUtil.copyProperties(form, product);
        product.setParameter((form.getParameter()));

        boolean ok = iProductService.save(product);
        if (ok) {
            String[] tags = form.getTags();
            for (String tag : tags) {
                Tag t = new Tag();
                t.setType(2);
                t.setName(tag).setLinkId(product.getProjectId());
                iTagService.save(t);
            }

        }
        return ok ? data(product) : fail();

    }

    /**
     * 删除产品
     *
     * @param ids 批量删除的ID数组:必传
     * @return
     */

    @DeleteMapping
    public R delete(@PathVariable Integer[] ids) {
        boolean ok = iProductService.removeByIds(Arrays.asList(ids));
        return ok ? success() : fail();
    }

    /**
     * 更新产品信息
     *
     * @param form 产品:必传
     * @return
     * @throws XException
     */
    @PutMapping
    public R update(@PathVariable Long id, @RequestBody @Valid UpdateProductForm form) throws XException {


        Product product = iProductService.getById(id);
        if (product == null) {
            throw new BizException("Product not exists!", "产品不存在");
        }
        if (!StringUtils.isEmpty(form.getName())) {

            product.setName(form.getName());
        }
        if (!StringUtils.isEmpty(form.getLogo())) {

            product.setLogo(form.getLogo());
        }
        if (!StringUtils.isEmpty(form.getType())) {

            product.setType(form.getType());
        }

        if (!StringUtils.isEmpty(form.getDescription())) {

            product.setDescription(form.getDescription());
        }
        if (form.getParameter() != null) {
            check(form.getParameter());
            product.setParameter((form.getParameter()));
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


    @GetMapping("/{id}")
    public R get(@PathVariable Long id) throws XException {
        Product project = iProductService.getById(id);
        if (project == null) {
            throw new BizException("Product not exists!", "产品不存在");
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
     * @param current   页码
     * @param size      页长
     * @return
     */
    @GetMapping
    public R queryForPage(
            @RequestParam Long projectId,
            @RequestParam Integer current,
            @RequestParam Integer size,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer type) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("project_id", projectId);
        queryWrapper.eq(tag != null, "tag", tag);
        queryWrapper.eq(type != null, "type", type);
        queryWrapper.like(name != null, "name", name);

        queryWrapper.orderByDesc("create_time");
        IPage<Product> projectPage = iProductService.page(new Page<>(current, size), queryWrapper);

        return data(projectPage);
    }


}

