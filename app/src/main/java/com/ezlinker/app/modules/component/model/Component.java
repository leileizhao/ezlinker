package com.ezlinker.app.modules.component.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 设备上面的模块，和设备是多对一关系
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants

@TableName("ez_component")
public class Component extends XEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @NotEmpty(message = "产品不可为空")
    private Integer productId;

    /**
     * 类型
     */
    @NotEmpty(message = "类型不可为空")

    private Integer type;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 名称
     */
    @NotEmpty(message = "模块名不可为空")

    private String name;

    /**
     * 协议
     */
    @NotEmpty(message = "协议不可为空")

    private Long protocol;

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
     * 密钥，基于计算生成的一个Base64字符串
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

}
