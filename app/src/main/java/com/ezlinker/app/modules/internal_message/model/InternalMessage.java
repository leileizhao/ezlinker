package com.ezlinker.app.modules.internal_message.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_internal_message")
public class InternalMessage extends XEntity {

    private static final long serialVersionUID=1L;

    private Integer type;

    private String content;

    private String title;

    private Integer status;

    private Integer userId;


}
