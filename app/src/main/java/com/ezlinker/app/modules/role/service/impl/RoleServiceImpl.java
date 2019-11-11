package com.ezlinker.app.modules.role.service.impl;

import com.ezlinker.app.modules.role.model.Role;
import com.ezlinker.app.modules.role.mapper.RoleMapper;
import com.ezlinker.app.modules.role.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
