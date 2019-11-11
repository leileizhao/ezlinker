package com.ezlinker.app.modules.userrole.service.impl;

import com.ezlinker.app.modules.userrole.model.UserRole;
import com.ezlinker.app.modules.userrole.mapper.UserRoleMapper;
import com.ezlinker.app.modules.userrole.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色关联 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-11
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
