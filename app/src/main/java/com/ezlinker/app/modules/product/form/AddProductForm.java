package com.ezlinker.app.modules.product.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;

/**
 * @program: ezlinker
 * @description: 添加新产品
 * @author: wangwenhai
 * @create: 2019-11-14 09:36
 **/
@Data
public class AddProductForm {
    /**
     * 项目ID
     */
    @NotEmpty(message = "项目不能为空")
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
    private HashMap<String, Object> parameter;

    /**
     * 描述文字
     */
    private String description;

}
