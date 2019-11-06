package com.ezlinker.app.modules.internal_message.service.impl;

import com.ezlinker.app.modules.internal_message.model.InternalMessage;
import com.ezlinker.app.modules.internal_message.mapper.InternalMessageMapper;
import com.ezlinker.app.modules.internal_message.service.IInternalMessageService;
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
public class InternalMessageServiceImpl extends ServiceImpl<InternalMessageMapper, InternalMessage> implements IInternalMessageService {

}
