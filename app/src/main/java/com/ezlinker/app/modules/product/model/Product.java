package com.ezlinker.app.modules.product.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    private static final long serialVersionUID=1L;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 产品名称
     */
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
    private String type;

    /**
     * 参数
     */
    private String parameter;

    /**
     * 描述文字
     */
    private String description;


}
