package com.ezlinker.app.modules.role.model;

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
@TableName("ez_role")
public class Role extends XEntity {

    private static final long serialVersionUID=1L;

    private String label;

    private String name;

    private Long parent;

    private String description;


}
