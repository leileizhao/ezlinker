package com.ezlinker.app.modules.user.model;

import com.ezlinker.app.modules.permission.model.Permission;
import com.ezlinker.app.modules.role.model.Role;
import lombok.Data;

import java.util.List;

/**
 * @program: ezlinker
 * @description: Token解析出来的User数据
 * @author: wangwenhai
 * @create: 2019-11-05 15:42
 **/
@Data
public class UserDetail {
    private Long id;
    private String username;
    private Integer userType;
    private Integer status;
    private List<Role> roles;
    private List<Permission> permissions;
    private Long expiredTime;

}
