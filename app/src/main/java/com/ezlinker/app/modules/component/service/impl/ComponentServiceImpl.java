package com.ezlinker.app.modules.component.service.impl;

import com.ezlinker.app.modules.component.model.Component;
import com.ezlinker.app.modules.component.mapper.ComponentMapper;
import com.ezlinker.app.modules.component.service.IComponentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@Service
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements IComponentService {

}
