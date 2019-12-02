package com.ezlinker.app.modules.feature.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 产品的功能（特性），和设备是多对多的关系，通过中间表关联起来
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "ez_feature", autoResultMap = true)
public class Feature extends XEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    private String name;

    /**
     * 标签
     */
    private String label;
    /**
     * 类型
     */
    private String type;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 命令Key
     */

    private String cmdKey = "cmdKey";

    /**
     * 命令Value
     */
    private String cmdValue = "cmdValue";

    @TableField(exist = false)
    private Long moduleId;

}
