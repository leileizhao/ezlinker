package com.ezlinker.app.modules.permission.model;

import lombok.Data;

@Data
public class UserRolePermissionView {
    private Long userId, roleId, permissionId;
    private String allow, resource, methods;
}
