package com.ezlinker.app.modules.permission.service.impl;

import com.ezlinker.app.modules.permission.model.Permission;
import com.ezlinker.app.modules.permission.mapper.PermissionMapper;
import com.ezlinker.app.modules.permission.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
