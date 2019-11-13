package com.ezlinker.app.modules.internalmessage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezlinker.app.modules.internalmessage.mapper.InternalMessageMapper;
import com.ezlinker.app.modules.internalmessage.model.InternalMessage;
import com.ezlinker.app.modules.internalmessage.service.InternalMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lc5900
 * @since 2019-11-13
 */
@Service
public class InternalMessageServiceImpl extends ServiceImpl<InternalMessageMapper, InternalMessage> implements InternalMessageService {

    private InternalMessageMapper internalMessageMapper;

    @Autowired
    public void setInternalMessageMapper(InternalMessageMapper internalMessageMapper) {
        this.internalMessageMapper = internalMessageMapper;
    }

    /**
     * 标记站内信
     *
     * @param id
     * @return
     */
    @Override
    public boolean mark(Long id) {
        return internalMessageMapper.mark(id);
    }
}
