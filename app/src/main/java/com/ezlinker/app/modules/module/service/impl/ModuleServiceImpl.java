package com.ezlinker.app.modules.module.service.impl;

import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.module.model.Module;
import com.ezlinker.app.modules.module.mapper.ModuleMapper;
import com.ezlinker.app.modules.module.service.IModuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {
    @Resource
    ModuleMapper moduleMapper;

    @Override
    public List<Feature> getFeatureList(Long moduleId) {
        return moduleMapper.getFeatureList(moduleId);
    }
}
