package com.ezlinker.app.modules.feature.model;

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
@TableName("ez_feature")
public class Feature extends XEntity {

    private static final long serialVersionUID=1L;

    private String label;

    private String name;

    private String type;

    private Integer productId;

    private String cmdKey;

    private String cmdValue;


}
