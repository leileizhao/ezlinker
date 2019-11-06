package com.ezlinker.app.modules.feature.model;

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
