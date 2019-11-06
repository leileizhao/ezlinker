package com.ezlinker.app.modules.product.model;

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
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_product")
public class Product extends XEntity {

    private static final long serialVersionUID=1L;

    private Integer projectId;

    private String name;

    private String logo;

    private String description;

    private String location;

    private String model;

    private String tag;

    private String industry;

    private String sn;

    private String type;

    private String parameter;

    private String runningStatus;


}
