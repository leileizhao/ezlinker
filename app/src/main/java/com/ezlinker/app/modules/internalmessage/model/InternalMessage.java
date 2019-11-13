package com.ezlinker.app.modules.internalmessage.model;

import lombok.Data;

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