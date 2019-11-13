package com.ezlinker.app.modules.internalmessage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezlinker.app.modules.internalmessage.model.InternalMessage;

/**
 * <p>
 * 站内信Mapper接口
 * </p>
 *
 * @author lc5900
 * @since 2019-11-13
 */
public interface InternalMessageMapper extends BaseMapper<InternalMessage> {
    /**
     * 标记站内信
     *
     * @param id
     * @return
     */
    boolean mark(Long id);
}