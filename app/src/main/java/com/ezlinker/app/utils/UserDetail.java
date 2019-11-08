package com.ezlinker.app.utils;

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
    /**
     * 用户ID
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 账号状态
     */
    private Integer status;
    /**
     * 过期时间
     */
    private Long expiredTime;
    /**
     * 角色列表
     */
    private List<String> roles;
    /**
     * 操作权限列表
     */
    private List<String> permissions;

}
