package com.ezlinker.app.modules.user.service.impl;

import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.mapper.UserMapper;
import com.ezlinker.app.modules.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
