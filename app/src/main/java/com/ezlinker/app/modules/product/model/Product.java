package com.ezlinker.app.modules.product.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

/**
 * <p>
 * 产品（设备的抽象模板）
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_product")
public class Product extends XEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 项目ID
     */
    @NotNull(message = "项目不能为空")
    private Integer projectId;

    /**
     * 产品名称
     */
    @NotEmpty(message = "名称不能为空")

    private String name;

    /**
     * 产品logo
     */
    private String logo;

    /**
     * 标签
     */
    private String tag;

    /**
     * 类型
     */
    @NotEmpty(message = "类型不能为空")
    private String type;

    /**
     * 参数
     */

    private String parameter;

    @TableField(exist = false)
    @JsonIgnore
    private JSONObject paramMap;
    /**
     * 描述文字
     */
    private String description;


}
