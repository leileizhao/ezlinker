package com.ezlinker.app.modules.product.service.impl;

import com.ezlinker.app.modules.product.model.Product;
import com.ezlinker.app.modules.product.mapper.ProductMapper;
import com.ezlinker.app.modules.product.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
