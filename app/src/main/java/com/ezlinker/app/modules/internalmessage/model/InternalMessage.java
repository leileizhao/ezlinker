package com.ezlinker.app.modules.internalmessage.model;

import com.baomidou.mybatisplus.annotation.TableName;
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
@EqualsAndHashCode()
@Accessors(chain = true)
@TableName("ez_internal_message")
public class InternalMessage {
    /**
     * PK
     */
    private Integer id;

    private Integer type;

    private String content;

    private String title;

    private Integer status;

    private Integer userId;

    /**
     * 记录版本
     */
    private Integer recordVersion;

    private Boolean x;

    private Date createTime;
}
