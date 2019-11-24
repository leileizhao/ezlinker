package com.ezlinker.app.modules.product.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ezlinker.common.model.XEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

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
@TableName(value = "ez_product", autoResultMap = true)
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
     * 类型
     */
    @NotEmpty(message = "类型不能为空")
    private String type;

    /**
     * 产品logo
     */
    private String logo;

    /**
     * 标签
     */
    private String tag;

    /**
     * 参数
     */


    @TableField(typeHandler = JacksonTypeHandler.class)

    private List<HashMap<String, Object>> parameter;

    /**
     * 描述文字
     */
    private String description;


}
