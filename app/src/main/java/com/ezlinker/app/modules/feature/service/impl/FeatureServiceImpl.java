package com.ezlinker.app.modules.feature.service.impl;

import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.feature.mapper.FeatureMapper;
import com.ezlinker.app.modules.feature.service.IFeatureService;
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
public class FeatureServiceImpl extends ServiceImpl<FeatureMapper, Feature> implements IFeatureService {

}
