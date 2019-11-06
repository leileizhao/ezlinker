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
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_component")
public class Component extends XEntity {

    private static final long serialVersionUID=1L;

    private Integer productId;

    private Integer type;

    private Integer state;

    private String name;

    private String description;

    private String protocol;

    private String model;

    private String tag;

    private String sn;

    private String token;

    private Integer isSuperuser;


}
