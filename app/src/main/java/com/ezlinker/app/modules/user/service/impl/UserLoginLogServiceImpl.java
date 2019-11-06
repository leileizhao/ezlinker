package com.ezlinker.app.modules.user.service.impl;

import com.ezlinker.app.modules.user.model.UserLoginLog;
import com.ezlinker.app.modules.user.mapper.UserLoginLogMapper;
import com.ezlinker.app.modules.user.service.IUserLoginLogService;
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
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogMapper, UserLoginLog> implements IUserLoginLogService {

}
