package com.ezlinker.app.modules.project.form;

import com.ezlinker.common.model.XFrom;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: ezlinker
 * @description: 新建项目的表单
 * @author: wangwenhai
 * @create: 2019-11-11 11:10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class AddProjectForm extends XFrom {
    private String name;

    private String logo;

    private Integer userId;

    private String description;

    private String location;

}
