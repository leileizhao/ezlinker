package com.ezlinker.app.modules.module.mapper;

import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.module.model.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 Mapper 接口
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
public interface ModuleMapper extends BaseMapper<Module> {

    List<Feature> getFeatureList(Long moduleId);
}
