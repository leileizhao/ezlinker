package com.ezlinker.app.modules.component.model;

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
 * @since 2019-11-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_component")
public class Component extends XEntity {

    private static final long serialVersionUID=1L;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 协议
     */
    private String protocol;

    /**
     * 型号
     */
    private String model;

    /**
     * 标签
     */
    private String tag;

    /**
     * 序列号
     */
    private String sn;

    /**
     * 密钥
     */
    private String token;

    /**
     * 是否是超级管理员
     */
    private Integer isSuperuser;

    /**
     * 数据域
     */
    private String dataArea;


}
