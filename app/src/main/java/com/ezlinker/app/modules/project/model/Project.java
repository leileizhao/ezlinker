package com.ezlinker.app.modules.project.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
@TableName("ez_project")
public class Project extends XEntity {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "项目名称不可为空")
    private String name;

    private String logo;
    @NotNull(message = "用户不可为空")
    private Integer userId;

    private String description;

    private String location;


}
