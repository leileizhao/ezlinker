package com.ezlinker.app.modules.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.app.modules.module.model.Module;

import java.util.List;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 服务类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
public interface IModuleService extends IService<Module> {
    List<Feature> getFeatureList(Long moduleId);

}
