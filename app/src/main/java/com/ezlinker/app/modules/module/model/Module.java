package com.ezlinker.app.modules.module.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ezlinker.app.modules.feature.model.Feature;
import com.ezlinker.common.model.XEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系
 * 目前此表记录了多种协议的设备数据
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants

@TableName(value = "ez_module", autoResultMap = true)
public class Module extends XEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @NotNull(message = "产品不可为空")
    private Long productId;

    /**
     * 类型
     */
    @NotNull(message = "类型不可为空")

    private Integer type;


    /**
     * 名称
     */
    @NotEmpty(message = "模块名不可为空")

    private String name;

    /**
     * 协议
     */
    @NotNull(message = "协议不可为空")

    private Long protocol;

    /**
     * 型号
     */

    private String model;


    /**
     * 序列号
     */
    private String sn;

    /**
     * 密钥，基于计算生成的一个Base64字符串
     */
    private String token;

    /**
     * 是否是超级管理员
     */
    @JsonIgnore
    private Integer isSuperuser;

    /**
     * 数据域
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<HashMap<String, Object>> dataArea;

    /**
     * 描述
     */
    private String description;

    /**
     * MQTT的用户名和密码
     */
    private String clientId;
    private String username;
    @JsonIgnore
    private String password;

    /**
     * 辅助字段，用来展示该模块支持的功能列表
     */
    @TableField(exist = false)
    private List<Feature> featureList;
}
