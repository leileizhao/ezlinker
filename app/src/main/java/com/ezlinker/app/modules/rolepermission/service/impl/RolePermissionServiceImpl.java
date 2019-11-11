package com.ezlinker.app.modules.rolepermission.service.impl;

import com.ezlinker.app.modules.rolepermission.model.RolePermission;
import com.ezlinker.app.modules.rolepermission.mapper.RolePermissionMapper;
import com.ezlinker.app.modules.rolepermission.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和权限关联表 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

}
