package com.ezlinker.app.modules.permission.model;

import lombok.Data;

@Data
public class RolePermissionView {

    private Long id;

    private Long roleId;

    private String label;

    private String name;

    private String resource;

    private Integer type;

    private Integer parent;

    private String description;

}
