package com.ezlinker.app.modules.internalmessage.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lc5900
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ez_internal_message")
public class InternalMessage extends XEntity {

    private Integer type;

    private String content;

    private String title;

    private Integer marked;

    private Integer userId;

}
