package com.ezlinker.app.modules.component.service.impl;

import com.ezlinker.app.modules.component.model.Component;
import com.ezlinker.app.modules.component.mapper.ComponentMapper;
import com.ezlinker.app.modules.component.service.IComponentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-07
 */
@Service
public class ComponentServiceImpl extends ServiceImpl<ComponentMapper, Component> implements IComponentService {

}
